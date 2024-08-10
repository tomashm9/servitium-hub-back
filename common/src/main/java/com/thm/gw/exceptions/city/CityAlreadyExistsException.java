package com.thm.gw.exceptions.city;

import com.thm.gw.exceptions.AlreadyExistsException;

public class CityAlreadyExistsException extends AlreadyExistsException {
    public CityAlreadyExistsException() {
        super("City with this name already exists");
    }
}
