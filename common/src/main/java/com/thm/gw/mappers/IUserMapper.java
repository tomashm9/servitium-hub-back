package com.thm.gw.mappers;

import com.thm.gw.dtos.user.*;
import com.thm.gw.entities.*;
import com.thm.gw.exceptions.auth.InvalidUserTypeException;
import com.thm.gw.forms.auth.AbstractRegisterForm;
import com.thm.gw.forms.auth.ClientRegisterForm;
import com.thm.gw.forms.auth.ManagerRegisterForm;
import com.thm.gw.forms.auth.OwnerRegisterForm;
import com.thm.gw.forms.user.UserUpdateForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    /**
     * Map an OwnerRegisterForm to an Owner entity.
     * @param form the request to map
     * @param roles the roles to assign to the user
     * @return the owner entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Owner toOwner(OwnerRegisterForm form, Set<Role> roles);

    /**
     * Map a ManagerRegisterForm to a Manager entity.
     * @param form the request to map
     * @param roles the roles to assign to the user
     * @return the manager entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Manager toManager(ManagerRegisterForm form, Set<Role> roles);

    /**
     * Map a ClientRegisterForm to a Client entity.
     * @param form the request to map
     * @param roles the roles to assign to the user
     * @return the client entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Client toClient(ClientRegisterForm form, Set<Role> roles);

    /**
     * Map an AbstractRegisterForm to a User entity.
     * @param request the request to map
     * @param roles the roles to assign to the user
     * @return the user entity
     */
    default User toEntity(AbstractRegisterForm request, Set<Role> roles) {
        if (request instanceof OwnerRegisterForm ownerRegisterForm) {
            return toOwner(ownerRegisterForm, roles);
        }

        if (request instanceof ManagerRegisterForm managerRegisterForm) {
            return toManager(managerRegisterForm, roles);
        }

        if (request instanceof ClientRegisterForm clientRegisterForm) {
            return toClient(clientRegisterForm, roles);
        }

        throw new InvalidUserTypeException();
    }

    default Set<String> map(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromForm(UserUpdateForm form, @MappingTarget User user);

    @Mapping(source = "enabled", target = "isEnabled")
    @Mapping(source = "locked", target = "isLocked")
    UserDTO fromUser(User user);

    @Mapping(source = "enabled", target = "isEnabled")
    @Mapping(source = "locked", target = "isLocked")
    OwnerDTO fromOwner(Owner owner);

    @Mapping(source = "enabled", target = "isEnabled")
    @Mapping(source = "locked", target = "isLocked")
    ManagerDTO fromManager(Manager manager);

    @Mapping(source = "enabled", target = "isEnabled")
    @Mapping(source = "locked", target = "isLocked")
    ClientDTO fromClient(Client client);

    OwnerProfileDTO fromOwnerProfile(Owner owner);

    ManagerProfileDTO fromManagerProfile(Manager manager);

    ClientProfileDTO fromClientProfile(Client client);

    UserUpdatePasswordDTO fromUserToUserUpdatedPassword(User user, String message);
}
