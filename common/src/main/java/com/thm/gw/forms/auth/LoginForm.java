package com.thm.gw.forms.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginForm(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {
}
