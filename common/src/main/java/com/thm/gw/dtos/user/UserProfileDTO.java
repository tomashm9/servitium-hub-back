package com.thm.gw.dtos.user;

import com.thm.gw.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public abstract class UserProfileDTO {
    private final Long id;
    private final String email;
    private final String firstname;
    private final String lastname;
    private final String phoneNumber;
    private final String contactEmail;
    private final Gender gender;
    private final LocalDate birthDate;

}