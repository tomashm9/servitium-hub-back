package com.thm.gw.forms.user;

import com.thm.gw.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserUpdateForm {
    @NotBlank(message = "There must be a firstname")
    private final String firstname;

    @NotBlank(message = "There must be lastname")
    private final String lastname;

    @NotBlank(message = "There must be a phone number")
    private final  String phoneNumber;

    @NotBlank(message = "There must be a contact email")
    @Email(message = "The contact email is not valid")
    private final String contactEmail;

    @NotBlank(message = "There must be a gender")
    @Enumerated(EnumType.STRING)
    private final Gender gender;

    @NotBlank(message = "There must be birth date")
    private final LocalDate birthDate;
}
