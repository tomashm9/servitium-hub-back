package com.thm.gw.exceptions.auth;

import com.thm.gw.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super("User does not exist !");
    }
}
