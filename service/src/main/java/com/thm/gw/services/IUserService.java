package com.thm.gw.services;

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

import java.util.List;

/**
 * The user service interface. Contains methods for managing users.
 * This service is intended to be used by users with the ADMIN role.
 */
public interface IUserService {

    /**
     * Retrieves all users.
     * @return A list of all users.
     */
    List<UserDTO> getAll();

    /**
     * Retrieves all owners.
     * @return A list of all owners.
     */
    List<OwnerDTO> getAllOwners();

    /**
     * Retrieves all clients.
     * @return A list of all clients.
     */
    List<ClientDTO> getAllClients();

    /**
     * Retrieves all managers.
     * @return A list of all managers.
     */
    List<ManagerDTO> getAllManagers();

    /**
     * Retrieves a user by their ID.
     * @param id The ID of the user to retrieve.
     * @return The user with the given ID.
     */
    UserDTO getUserById(Long id);

    /**
     * Retrieves a user by their email.
     * @param email The email of the user to retrieve.
     * @return The user with the given email.
     */
    UserDTO getUserByEmail(String email);

    /**
     * Adds a new owner.
     * @param form The form containing the owner's information.
     * @return The added owner.
     */
    OwnerDTO addOwner(OwnerRegisterForm form);

    /**
     * Adds a new manager.
     * @param form The form containing the manager's information.
     * @return The added manager.
     */
    ManagerDTO addManager(ManagerRegisterForm form);

    /**
     * Adds a new client.
     * @param form The form containing the client's information.
     * @return The added client.
     */
    ClientDTO addClient(ClientRegisterForm form);

    /**
     * Updates an existing owner.
     * @param id The ID of the owner to update.
     * @param form The form containing the new owner's information.
     * @return The updated owner.
     */
    OwnerDTO updateOwner(Long id, OwnerUpdateForm form);

    /**
     * Updates an existing manager.
     * @param id The ID of the manager to update.
     * @param form The form containing the new manager's information.
     * @return The updated manager.
     */
    ManagerDTO updateManager(Long id, ManagerUpdateForm form);

    /**
     * Updates an existing client.
     * @param id The ID of the client to update.
     * @param form The form containing the new client's information.
     * @return The updated client.
     */
    ClientDTO updateClient(Long id, ClientUpdateForm form);

    /**
     * Toggles the lock status of a user.
     * @param id The ID of the user to lock or unlock.
     * @param isLocked Whether to lock or unlock the user.
     * @return The updated user with the new lock status.
     */
    UserDTO triggerLock(Long id, boolean isLocked);

    /**
     * Toggles the enable status of a user.
     * @param id The ID of the user to enable or disable.
     * @param isEnabled Whether to enable or disable the user.
     * @return The updated user with the new enable status.
     */
    UserDTO triggerEnable(Long id, boolean isEnabled);
}
