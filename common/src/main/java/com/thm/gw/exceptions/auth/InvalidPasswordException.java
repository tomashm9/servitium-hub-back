package com.thm.gw.exceptions.auth;

import com.thm.gw.exceptions.NotAllowedException;

public class InvalidPasswordException extends NotAllowedException {

    public InvalidPasswordException() {
        super("Invalid password!");
    }
}
