package com.thm.gw.mappers;

import com.thm.gw.dtos.role.RoleDTO;
import com.thm.gw.entities.Role;
import com.thm.gw.forms.role.RoleForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Role toEntity(RoleForm request);

    RoleDTO fromEntity(Role entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(RoleForm request, @MappingTarget Role role);
}
