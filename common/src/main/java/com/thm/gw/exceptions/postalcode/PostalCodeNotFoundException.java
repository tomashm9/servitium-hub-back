package com.thm.gw.exceptions.postalcode;

import com.thm.gw.exceptions.NotFoundException;

public class PostalCodeNotFoundException extends NotFoundException {
    public PostalCodeNotFoundException() {
        super("PostalCode not found");
    }
}
