package com.thm.gw.exceptions.city;

import com.thm.gw.exceptions.NotFoundException;

public class CityNotFoundException extends NotFoundException{
    public CityNotFoundException() {
        super("City not found");
    }
}
