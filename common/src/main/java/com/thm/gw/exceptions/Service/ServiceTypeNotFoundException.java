package com.thm.gw.exceptions.Service;

import com.thm.gw.exceptions.NotFoundException;

public class ServiceTypeNotFoundException extends NotFoundException {

    public ServiceTypeNotFoundException() {
        super("ServiceType not found");
    }
}
