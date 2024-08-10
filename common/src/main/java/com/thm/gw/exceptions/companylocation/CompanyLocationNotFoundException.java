package com.thm.gw.exceptions.companylocation;

import com.thm.gw.exceptions.NotFoundException;

public class CompanyLocationNotFoundException extends NotFoundException {

    public CompanyLocationNotFoundException() {
        super("Company location not found");
    }
}
