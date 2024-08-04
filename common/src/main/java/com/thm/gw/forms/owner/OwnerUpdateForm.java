package com.thm.gw.forms.owner;

import com.thm.gw.enums.UserType;
import jakarta.validation.constraints.NotNull;

public record OwnerUpdateForm(
        @NotNull(message = "The owner role is required")
        UserType ownerRole
) { }
