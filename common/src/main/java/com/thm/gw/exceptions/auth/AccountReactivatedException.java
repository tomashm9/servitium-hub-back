package com.thm.gw.exceptions.auth;

public class AccountReactivatedException extends RuntimeException {

    public AccountReactivatedException() {
        super("Your account has been reactivated! Please login again.");
    }
}
