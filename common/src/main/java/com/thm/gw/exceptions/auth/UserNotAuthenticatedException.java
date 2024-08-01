package com.thm.gw.exceptions.auth;

import com.thm.gw.exceptions.NotAllowedException;

public class UserNotAuthenticatedException extends NotAllowedException {

        public UserNotAuthenticatedException() {
            super("You are not authenticated !");
        }
}
