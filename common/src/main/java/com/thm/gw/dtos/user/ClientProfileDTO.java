package com.thm.gw.dtos.user;

import com.thm.gw.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientProfileDTO extends UserProfileDTO {
    public ClientProfileDTO(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            Gender gender,
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
