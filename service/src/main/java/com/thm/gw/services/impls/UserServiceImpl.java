package com.thm.gw.services.impls;

import com.thm.gw.dtos.user.ClientDTO;
import com.thm.gw.dtos.user.ManagerDTO;
import com.thm.gw.dtos.user.OwnerDTO;
import com.thm.gw.dtos.user.UserDTO;
import com.thm.gw.entities.*;
import com.thm.gw.exceptions.NotAllowedException;
import com.thm.gw.exceptions.NotFoundException;
import com.thm.gw.exceptions.auth.UserAlreadyExistsException;
import com.thm.gw.exceptions.auth.UserNotFoundException;
import com.thm.gw.forms.auth.ClientRegisterForm;
import com.thm.gw.forms.auth.ManagerRegisterForm;
import com.thm.gw.forms.auth.OwnerRegisterForm;
import com.thm.gw.forms.user.ClientUpdateForm;
import com.thm.gw.forms.user.ManagerUpdateForm;
import com.thm.gw.forms.user.OwnerUpdateForm;
import com.thm.gw.mappers.IUserMapper;
import com.thm.gw.repositories.*;
import com.thm.gw.services.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IOwnerRepository ownerRepository;
    private final IManagerRepository managerRepository;
    private final IClientRepository clientRepository;
    private final IRoleRepository roleRepository;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::fromUser)
                .toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.fromUser(
                userRepository.findById(id).orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userMapper.fromUser(
                userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public List<OwnerDTO> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map(userMapper::fromOwner)
                .toList();
    }

    @Override
    public List<ManagerDTO> getAllManagers() {
        return managerRepository.findAll().stream()
                .map(userMapper::fromManager)
                .toList();
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(userMapper::fromClient)
                .toList();
    }

    @Override
    @Transactional
    public OwnerDTO addOwner(OwnerRegisterForm form) {
        if (userRepository.findByEmail(form.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Role role = roleRepository.findByName(form.getRoleName())
                .orElseThrow(() -> new NotFoundException(form.getRoleName() + " role not found"));

        form.setPassword(passwordEncoder.encode(form.getPassword()));

        Owner owner = userMapper.toOwner(form, Set.of(role));

        return userMapper.fromOwner(ownerRepository.save(owner));
    }

    @Override
    @Transactional
    public ManagerDTO addManager(ManagerRegisterForm form) {
        if (userRepository.findByEmail(form.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Role role = roleRepository.findByName(form.getRoleName())
                .orElseThrow(() -> new NotFoundException(form.getRoleName() + " role not found"));

        form.setPassword(passwordEncoder.encode(form.getPassword()));

        Manager manager = userMapper.toManager(form, Set.of(role));

        return userMapper.fromManager(managerRepository.save(manager));
    }

    @Override
    @Transactional
    public ClientDTO addClient(ClientRegisterForm form) {
        if (userRepository.findByEmail(form.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Role role = roleRepository.findByName(form.getRoleName())
                .orElseThrow(() -> new NotFoundException(form.getRoleName() + " role not found"));

        form.setPassword(passwordEncoder.encode(form.getPassword()));

        Client client = userMapper.toClient(form, Set.of(role));

        return userMapper.fromClient(clientRepository.save(client));
    }

    @Override
    @Transactional
    public OwnerDTO updateOwner(Long id, OwnerUpdateForm form) {
        Owner owner = ownerRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userMapper.updateEntityFromForm(form, owner);

        return userMapper.fromOwner(userRepository.save(owner));
    }

    @Override
    @Transactional
    public ManagerDTO updateManager(Long id, ManagerUpdateForm form) {
        Manager manager = managerRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userMapper.updateEntityFromForm(form, manager);

        return userMapper.fromManager(userRepository.save(manager));
    }

    @Override
    @Transactional
    public ClientDTO updateClient(Long id, ClientUpdateForm form) {
        Client client = clientRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userMapper.updateEntityFromForm(form, client);

        return userMapper.fromClient(userRepository.save(client));
    }

    @Override
    public UserDTO triggerLock(Long id, boolean isLocked) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (!user.isAccountNonLocked() == isLocked) {
            throw new NotAllowedException(String.format("User field 'isLocked' already defined to '%s'", isLocked));
        }

        user.setLocked(isLocked);

        return userMapper.fromUser(userRepository.save(user));
    }

    @Override
    public UserDTO triggerEnable(Long id, boolean isEnabled) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (!user.isAccountNonLocked() == isEnabled) {
            throw new NotAllowedException(String.format("User field 'isEnabled' already defined to '%s'", isEnabled));
        }

        user.setEnabled(isEnabled);

        return userMapper.fromUser(userRepository.save(user));
    }
}
