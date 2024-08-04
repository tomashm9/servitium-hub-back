package com.thm.gw.dtos.user;

import com.thm.gw.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class OwnerDTO extends UserDTO{
    public OwnerDTO(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            Gender gender,
            LocalDate birthDate,
            Set<String> roles,
            boolean isEnabled,
            boolean isLocked
    ) {
        super(
                id,
                email,
                firstname,
                lastname,
                phoneNumber,
                contactEmail,
                gender,
                birthDate,
                roles,
                isEnabled,
                isLocked
        );
    }
}
