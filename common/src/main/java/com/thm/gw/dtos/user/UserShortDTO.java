package com.thm.gw.dtos.user;

import com.thm.gw.enums.Gender;

import java.time.LocalDate;

public record UserShortDTO(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String contactEmail,
        Gender gender,
        LocalDate birthDate
) { }
