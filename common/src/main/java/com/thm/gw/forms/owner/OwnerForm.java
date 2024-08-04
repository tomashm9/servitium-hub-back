package com.thm.gw.forms.owner;

import com.thm.gw.enums.CompanyRole;
import jakarta.validation.constraints.NotNull;

public record OwnerForm(
        @NotNull(message = "The owner id is required")
        Long ownerId,

        @NotNull(message = "The owner role is required")
        CompanyRole ownerRole
) { }
