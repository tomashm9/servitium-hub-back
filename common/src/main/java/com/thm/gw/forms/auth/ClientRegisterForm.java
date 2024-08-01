package com.thm.gw.forms.auth;

import com.thm.gw.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ClientRegisterForm extends AbstractRegisterForm{

    private static final String ROLE_NAME = UserType.CLIENT.name();

    @Override
    public String getRoleName() {
        return ROLE_NAME;
    }
}
