package com.thm.gw.exceptions.company;

import com.thm.gw.exceptions.NotFoundException;

public class CompanyNotFoundException extends NotFoundException {

        public CompanyNotFoundException() {
            super("Company not found.");
        }
}
