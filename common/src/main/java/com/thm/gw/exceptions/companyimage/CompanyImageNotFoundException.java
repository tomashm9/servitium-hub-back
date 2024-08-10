package com.thm.gw.exceptions.companyimage;

import com.thm.gw.exceptions.NotFoundException;

public class CompanyImageNotFoundException extends NotFoundException {
    public CompanyImageNotFoundException() {
        super("Company image not found");
    }
}
