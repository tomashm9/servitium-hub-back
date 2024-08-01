package com.thm.gw.exceptions;

public class ResourceAccessDeniedException extends RuntimeException {

    public ResourceAccessDeniedException() {
        super("Access denied ! You are not allowed to access this resource !");
    }

    public ResourceAccessDeniedException(String message) {
        super(message);
    }
}
