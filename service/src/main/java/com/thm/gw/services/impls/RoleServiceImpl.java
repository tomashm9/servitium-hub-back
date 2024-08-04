package com.thm.gw.services.impls;

import com.thm.gw.dtos.role.RoleDTO;
import com.thm.gw.entities.Role;
import com.thm.gw.exceptions.AlreadyExistsException;
import com.thm.gw.exceptions.role.RoleNotFoundException;
import com.thm.gw.forms.role.RoleForm;
import com.thm.gw.mappers.IRoleMapper;
import com.thm.gw.repositories.IRoleRepository;
import com.thm.gw.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;

    @Override
    public List<RoleDTO> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::fromEntity)
                .toList();
    }

    @Override
    public RoleDTO getByName(String name) {
        Role role = roleRepository.findByName(name).orElseThrow(RoleNotFoundException::new);

        return roleMapper.fromEntity(role);
    }

    @Override
    public RoleDTO getById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);

        return roleMapper.fromEntity(role);
    }

    @Override
    public RoleDTO add(RoleForm form) {
        if (roleRepository.findByName(form.name()).isPresent()) {
            throw new AlreadyExistsException("Role already exists");
        }

        Role role = roleMapper.toEntity(form);

        return roleMapper.fromEntity(roleRepository.save(role));
    }

    @Override
    public RoleDTO update(Long id, RoleForm form) {
        Role existingRole = roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);

        roleMapper.updateEntityFromRequest(form, existingRole);

        return roleMapper.fromEntity(roleRepository.save(existingRole));
    }

    @Override
    public RoleDTO delete(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);

        roleRepository.delete(role);

        return roleMapper.fromEntity(role);
    }
}
