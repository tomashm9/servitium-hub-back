package com.thm.gw.forms.role;

import jakarta.validation.constraints.NotBlank;

public record RoleForm(

        @NotBlank(message = "Role name cannot be blank")
        String name,

        @NotBlank(message = "Role description cannot be blank")
        String description
) { }
