package com.thm.gw.forms.auth;

import com.thm.gw.enums.Gender;
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
    private String firstName;

    @NotBlank(message = "There must be lastname")
    private String lastName;

    @NotBlank(message = "There must be a phone number")
    private String phoneNumber;

    @NotBlank(message = "There must be a contact email")
    @Email(message = "The contact email is not valid")
    private String contactEmail;

    @NotNull(message = "There must be a gender")
    private Gender gender;

    @NotNull(message = "There must be birth date")
    private LocalDate birthDate;

    public abstract String getRoleName();
}
