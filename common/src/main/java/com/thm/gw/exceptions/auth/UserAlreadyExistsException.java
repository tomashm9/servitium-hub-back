package com.thm.gw.exceptions.auth;

import com.thm.gw.exceptions.AlreadyExistsException;

public class UserAlreadyExistsException extends AlreadyExistsException {

    public UserAlreadyExistsException() {
        super("User already exists !");
    }
}
