package com.thm.gw.exceptions.auth;

import com.thm.gw.exceptions.NotAllowedException;

public class InvalidUserTypeException extends NotAllowedException {
    public InvalidUserTypeException() {
        super("Invalid user type!");
    }
}