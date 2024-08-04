package com.thm.gw.services;

import com.thm.gw.dtos.role.RoleDTO;
import com.thm.gw.forms.role.RoleForm;

import java.util.List;

/**
 * Service interface for role management in the system.
 * Provides basic CRUD operations for roles.
 */
public interface IRoleService {

    /**
     * Retrieves a list of all roles.
     * @return a list of role responses.
     */
    List<RoleDTO> getAll();

    /**
     * Retrieves a role by its name.
     * @param name the name of the role.
     * @return a role response.
     */
    RoleDTO getByName(String name);

    /**
     * Retrieves a role by its id.
     * @param id the id of the role.
     * @return a role response.
     */
    RoleDTO getById(Long id);

    /**
     * Adds a new role.
     * @param form the role request.
     * @return a role response.
     */
    RoleDTO add(RoleForm form);

    /**
     * Updates a role by its id.
     * @param id the id of the role to update.
     * @param form the role request.
     * @return a role response.
     */
    RoleDTO update(Long id, RoleForm form);

    /**
     * Deletes a role by its id.
     * @param id the id of the role to delete.
     * @return a role response.
     */
    RoleDTO delete(Long id);
}
