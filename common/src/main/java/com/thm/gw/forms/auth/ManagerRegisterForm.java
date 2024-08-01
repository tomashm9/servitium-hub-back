package com.thm.gw.forms.auth;

import com.thm.gw.enums.UserType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ManagerRegisterForm extends AbstractRegisterForm {

    private static final String ROLE_NAME = UserType.MANAGER.name();

    @Override
    public String getRoleName() {
        return ROLE_NAME;
    }
}
