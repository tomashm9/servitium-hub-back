package com.thm.gw.forms.user;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OwnerUpdateForm extends UserUpdateForm {
    public OwnerUpdateForm(
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            String gender,
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
