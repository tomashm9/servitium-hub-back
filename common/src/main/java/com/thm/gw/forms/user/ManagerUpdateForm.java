package com.thm.gw.forms.user;

import com.thm.gw.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ManagerUpdateForm extends UserUpdateForm {
    public ManagerUpdateForm(
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            Gender gender,
            LocalDate birthDate
    ) {
        super(
                firstname,
                lastname,
                phoneNumber,
                contactEmail,
                gender,
                birthDate
        );
    }
}
