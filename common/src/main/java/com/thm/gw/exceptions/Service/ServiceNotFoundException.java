package com.thm.gw.exceptions.Service;

import com.thm.gw.exceptions.NotFoundException;

public class ServiceNotFoundException extends NotFoundException {

    public ServiceNotFoundException() {
        super("Service not found: ");
    }
}
