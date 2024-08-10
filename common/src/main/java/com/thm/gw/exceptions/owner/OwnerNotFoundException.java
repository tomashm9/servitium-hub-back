package com.thm.gw.exceptions.owner;

import com.thm.gw.exceptions.NotFoundException;

public class OwnerNotFoundException extends NotFoundException {

    public OwnerNotFoundException() {
        super("Owner not found");
    }
}
