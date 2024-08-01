package com.thm.gw.dtos.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OwnerProfileDTO extends UserProfileDTO {
    public OwnerProfileDTO(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            String gender,
            LocalDate birthDate
    ) {
        super(
                id,
                email,
                firstname,
                lastname,
                phoneNumber,
                contactEmail,
                gender,
                birthDate
        );
    }
}
