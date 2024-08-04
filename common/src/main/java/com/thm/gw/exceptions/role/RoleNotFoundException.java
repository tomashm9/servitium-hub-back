package com.thm.gw.exceptions.role;

import com.thm.gw.exceptions.NotFoundException;

public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException() {
        super("Role not found");
    }
}
