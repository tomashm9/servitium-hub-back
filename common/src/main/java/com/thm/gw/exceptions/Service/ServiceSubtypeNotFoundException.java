package com.thm.gw.exceptions.Service;

import com.thm.gw.exceptions.NotFoundException;

public class ServiceSubtypeNotFoundException extends NotFoundException {

    public ServiceSubtypeNotFoundException() {
        super("ServiceSubtype not found");
    }
}
