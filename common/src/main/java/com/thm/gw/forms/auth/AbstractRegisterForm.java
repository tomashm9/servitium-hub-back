package com.thm.gw.forms.auth;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public abstract class AbstractRegisterForm {

    @NotBlank(message = "There must be an email")
    @Email(message = "The email is not valid")
    private String email;

    @NotNull(message = "There must be a phone number")
    @Size(min = 8, message = "Password must be 8 characters minimum")
    private String password;

    @NotBlank(message = "There must be a firstname")
    private String firstname;

    @NotBlank(message = "There must be lastname")
    private String lastname;

    @NotBlank(message = "There must be a phone number")
    private String phoneNumber;

    @NotBlank(message = "There must be a contact email")
    @Email(message = "The contact email is not valid")
    private String contactEmail;

    @NotBlank(message = "There must be a gender")
    private String gender;

    @NotBlank(message = "There must be birth date")
    private LocalDate birthDate;

    public abstract String getRoleName();
}
