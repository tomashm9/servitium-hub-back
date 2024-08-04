package com.thm.gw.dtos.company;

import java.time.LocalDate;

public record CompanyDTO(
        Long id,
        String name,
        String websiteUrl,
        LocalDate establishmentDate,
        String contactName,
        String contactPhoneNumber,
        boolean isActive
) { }
