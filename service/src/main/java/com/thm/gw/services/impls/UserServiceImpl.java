package com.thm.gw.services.impls;

import com.thm.gw.dtos.user.ClientDTO;
import com.thm.gw.dtos.user.ManagerDTO;
import com.thm.gw.dtos.user.OwnerDTO;
import com.thm.gw.dtos.user.UserDTO;
import com.thm.gw.forms.auth.ClientRegisterForm;
import com.thm.gw.forms.auth.ManagerRegisterForm;
import com.thm.gw.forms.auth.OwnerRegisterForm;
import com.thm.gw.forms.user.ClientUpdateForm;
import com.thm.gw.forms.user.ManagerUpdateForm;
import com.thm.gw.forms.user.OwnerUpdateForm;
import com.thm.gw.mappers.IUserMapper;
import com.thm.gw.repositories.IUserRepository;
import com.thm.gw.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::fromUser)
                .toList();
    }

    @Override
    public List<OwnerDTO> getAllOwners() {
        return List.of();
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return List.of();
    }

    @Override
    public List<ManagerDTO> getAllManagers() {
        return List.of();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public OwnerDTO addOwner(OwnerRegisterForm form) {
        return null;
    }

    @Override
    public ManagerDTO addManager(ManagerRegisterForm form) {
        return null;
    }

    @Override
    public ClientDTO addClient(ClientRegisterForm form) {
        return null;
    }

    @Override
    public OwnerDTO updateOwner(Long id, OwnerUpdateForm form) {
        return null;
    }

    @Override
    public ManagerDTO updateManager(Long id, ManagerUpdateForm form) {
        return null;
    }

    @Override
    public ClientDTO updateClient(Long id, ClientUpdateForm form) {
        return null;
    }

    @Override
    public UserDTO triggerLock(Long id, boolean isLocked) {
        return null;
    }

    @Override
    public UserDTO triggerEnable(Long id, boolean isEnabled) {
        return null;
    }
}
