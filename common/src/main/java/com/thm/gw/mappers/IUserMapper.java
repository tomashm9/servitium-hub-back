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

    /**
     * Map a set of roles to a set of role names.
     * @param roles the set of roles to map
     * @return the set of role names
     */
    default Set<String> map(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    /**
     * Update a user entity with the data from a UserUpdateRequest.
     * @param form the request to update the user with
     * @param user the user to update
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(UserUpdateForm form, @MappingTarget User user);

    /**
     * Map a User entity to a UserDTO.
     * @param user the user to map
     * @return the user dto
     */
    @Mapping(source = "enabled", target = "isEnabled")
    @Mapping(source = "locked", target = "isLocked")
    UserDTO fromUser(User user);

    /**
     * Map a Owner entity to a OwnerDTO.
     * @param owner the advertiser to map
     * @return the owner dto
     */
    @Mapping(source = "enabled", target = "isEnabled")
    @Mapping(source = "locked", target = "isLocked")
    OwnerDTO fromOwner(Owner owner);

    /**
     * Map a Manager entity to a ManagerDTO.
     * @param manager the manager to map
     * @return the manager dto
     */
    @Mapping(source = "enabled", target = "isEnabled")
    @Mapping(source = "locked", target = "isLocked")
    ManagerDTO fromManager(Manager manager);

    /**
     * Map a Client entity to a ClientDTO.
     * @param client the manager to map
     * @return the client dto
     */
    @Mapping(source = "enabled", target = "isEnabled")
    @Mapping(source = "locked", target = "isLocked")
    ClientDTO fromClient(Client client);

    /**
     * Map an Owner entity to an OwnerProfileDTO.
     * @param owner the owner to map
     * @return the owner profile dto
     */
    OwnerProfileDTO fromOwnerProfile(Owner owner);

    /**
     * Map a Manager entity to a ManagerProfileDTO.
     * @param manager the manager to map
     * @return the owner profile dto
     */
    ManagerProfileDTO fromManagerProfile(Manager manager);

    /**
     * Map a Client entity to a ClientProfileDTO.
     * @param client the client to map
     * @return the client profile dto
     */
    ClientProfileDTO fromClientProfile(Client client);

    /**
     * Map a User entity to a UserUpdatePasswordDTO.
     * @param user the user to map
     * @param message the message displayed to the user
     * @return the user password update dto
     */
    UserUpdatePasswordDTO fromUserToUserUpdatedPassword(User user, String message);
}
