package com.thm.gw.dtos.owner;

import com.thm.gw.dtos.company.CompanyDTO;
import com.thm.gw.dtos.user.UserShortDTO;
import com.thm.gw.enums.UserType;

import java.time.LocalDateTime;

public record OwnerDTO(
        Long id,
        UserShortDTO manager,
        CompanyDTO company,
        UserType ownerRole,
        LocalDateTime createdAt
) {
}
