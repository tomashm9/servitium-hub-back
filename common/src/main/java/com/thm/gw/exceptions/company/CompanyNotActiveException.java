package com.thm.gw.exceptions.company;

import com.thm.gw.exceptions.NotAllowedException;

public class CompanyNotActiveException extends NotAllowedException {

    public CompanyNotActiveException() {
        super("Company is not active.");
    }
}
