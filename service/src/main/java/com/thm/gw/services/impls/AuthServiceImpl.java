package com.thm.gw.services.impls;

import com.thm.gw.dtos.auth.UserTokenDTO;
import com.thm.gw.entities.*;
import com.thm.gw.exceptions.NotAllowedException;
import com.thm.gw.exceptions.NotFoundException;
import com.thm.gw.exceptions.auth.AccountReactivatedException;
import com.thm.gw.exceptions.auth.InvalidPasswordException;
import com.thm.gw.exceptions.auth.UserAlreadyExistsException;
import com.thm.gw.exceptions.auth.UserNotAuthenticatedException;
import com.thm.gw.forms.auth.AbstractRegisterForm;
import com.thm.gw.forms.auth.LoginForm;
import com.thm.gw.mappers.IUserMapper;
import com.thm.gw.repositories.IRoleRepository;
import com.thm.gw.repositories.IUserRepository;
import com.thm.gw.services.IAuthService;
import com.thm.gw.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.thm.gw.utils.Constants.ADMIN_ROLE;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserDetailsService, IAuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserMapper userMapper;
    private final UserDetailsChecker userDetailsChecker;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserTokenDTO login(LoginForm form) {
        User user = userRepository.findByEmail(form.email())
                .orElseThrow(UserNotAuthenticatedException::new);

        if (!passwordEncoder.matches(form.password(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        if (!user.isEnabled() && user.isAccountNonLocked()) {
            user.setEnabled(true);
            userRepository.save(user);

            throw new AccountReactivatedException();
        }

        userDetailsChecker.check(user);

        String token = jwtUtils.generateToken(user);

        return UserTokenDTO.fromEntityWithToken(user, token);
    }

    @Override
    public UserTokenDTO register(AbstractRegisterForm form) {
        if (userRepository.findByEmail(form.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Role role = roleRepository.findByName(form.getRoleName())
                .orElseThrow(() -> new NotFoundException(form.getRoleName() + " role not found"));

        form.setPassword(passwordEncoder.encode(form.getPassword()));

        User user = userMapper.toEntity(form, Set.of(role));

        userRepository.save(user);

        String token = jwtUtils.generateToken(user);

        return UserTokenDTO.fromEntityWithToken(user, token);
    }

    @Override
    public Owner getAuthenticatedOwner() throws UserNotAuthenticatedException {
        User authenticatorUser = getAuthenticatedUser();

        if (authenticatorUser instanceof Owner owner) {
            return owner;
        }

        throw new UserNotAuthenticatedException();
    }

    @Override
    public Manager getAuthenticatedManager() throws UserNotAuthenticatedException {
        User authenticatorUser = getAuthenticatedUser();

        if (authenticatorUser instanceof Manager manager) {
            return manager;
        }

        throw new UserNotAuthenticatedException();
    }

    @Override
    public Client getAuthenticatedClient() throws UserNotAuthenticatedException {
        User authenticatorUser = getAuthenticatedUser();

        if (authenticatorUser instanceof Client client) {
            return client;
        }

        throw new UserNotAuthenticatedException();
    }

    @Override
    public User getAuthenticatedUser() throws UserNotAuthenticatedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthenticatedException();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof User authenticatedUser) {
            return authenticatedUser;
        }

        throw new UserNotAuthenticatedException();
    }

    @Override
    public UserTokenDTO impersonateUser(User user) {
        if (!user.isEnabled() || !user.isAccountNonLocked() || user.isExpired()) {
            throw new NotAllowedException("User cannot be impersonated due to account restrictions");
        }

        String token = jwtUtils.generateToken(user);

        return UserTokenDTO.fromEntityWithToken(user, token);
    }

    @Override
    public boolean isAdmin(User user) {
        return user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(ADMIN_ROLE));
    }

    @Override
    public void inviteManager(String email) {
    }
}
