package com.thm.gw.dtos.user;

import java.time.LocalDate;

public record UserShortDTO(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String contactEmail,
        String gender,
        LocalDate birthDate
) { }
