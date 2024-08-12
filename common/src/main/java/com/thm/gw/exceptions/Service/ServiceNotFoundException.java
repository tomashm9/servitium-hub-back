package com.thm.gw.exceptions.Service;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException() {
        super("Service not found");
    }

    public ServiceNotFoundException(String message) {
        super(message);
    }
}