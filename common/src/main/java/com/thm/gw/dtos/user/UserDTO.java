package com.thm.gw.dtos.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UserDTO {
    protected final Long id;
    protected final String email;
    protected final String firstname;
    protected final String lastname;
    protected final String phoneNumber;
    protected final String contactEmail;
    private final String gender;
    private final LocalDate birthDate;
    protected final Set<String> roles;
    protected final boolean isEnabled;
    protected final boolean isLocked;

    public UserDTO(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            String gender,
            LocalDate birthDate,
            Set<String> roles,
            boolean isEnabled,
            boolean isLocked
    ) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.contactEmail = contactEmail;
        this.gender = gender;
        this.birthDate = birthDate;
        this.roles = roles;
        this.isEnabled = isEnabled;
        this.isLocked = isLocked;
    }
}
