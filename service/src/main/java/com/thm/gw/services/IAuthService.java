package com.thm.gw.services;

import com.thm.gw.dtos.auth.UserTokenDTO;
import com.thm.gw.entities.Client;
import com.thm.gw.entities.Manager;
import com.thm.gw.entities.Owner;
import com.thm.gw.entities.User;
import com.thm.gw.exceptions.auth.InvalidPasswordException;
import com.thm.gw.exceptions.auth.UserAlreadyExistsException;
import com.thm.gw.exceptions.auth.UserNotAuthenticatedException;
import com.thm.gw.exceptions.auth.UserNotFoundException;
import com.thm.gw.forms.auth.AbstractRegisterForm;
import com.thm.gw.forms.auth.LoginForm;

public interface IAuthService {

    /**
     * Authenticates a user with the provided login credentials.
     * @param form the login form containing the user's username and password
     * @return a UserTokenDTO object containing the user's details and authentication token
     * @throws InvalidPasswordException if the provided password does not match the one stored in the database
     * @throws UserNotFoundException if a user with the provided username does not exist in the database
     */
    UserTokenDTO login(LoginForm form);

    /**
     * Registers a new user in the system.
     * @param form the registration form containing the user's details and desired user type
     * @return a UserTokenDTO object containing the user's details and authentication token
     * @throws UserAlreadyExistsException if a user with the provided email already exists in the database
     */
    UserTokenDTO register(AbstractRegisterForm form);

    /**
     * Retrieves the currently authenticated owner.
     * @return the authenticated owner
     * @throws UserNotAuthenticatedException if no advertiser is currently authenticated
     */
    Owner getAuthenticatedOwner() throws UserNotAuthenticatedException;

    /**
     * Retrieves the currently authenticated manager.
     * @return the authenticated manager
     * @throws UserNotAuthenticatedException if no manager is currently authenticated
     */
    Manager getAuthenticatedManager() throws UserNotAuthenticatedException;

    /**
     * Retrieves the currently authenticated client.
     * @return the authenticated client
     * @throws UserNotAuthenticatedException if no client is currently authenticated
     */
    Client getAuthenticatedClient() throws UserNotAuthenticatedException;

    /**
     * Retrieves the currently authenticated user.
     * @return the authenticated user
     * @throws UserNotAuthenticatedException if no user is currently authenticated
     */
    User getAuthenticatedUser() throws UserNotAuthenticatedException;

    /**
     * Checks if the user is an admin.
     * @return true if the user is an admin, false otherwise
     */
    boolean isAdmin(User user);

    void inviteManager(String email);

    /**
     * Impersonates a user.
     * @param user the user to impersonate
     * @return a UserTokenResponse object containing the user's details and authentication token
     */
    UserTokenDTO impersonateUser(User user);
}
