package com.thm.gw.exceptions.country;

import com.thm.gw.exceptions.NotFoundException;

public class CountryNotFoundException extends NotFoundException {
    public CountryNotFoundException() {
        super("Country not found");
    }
}
