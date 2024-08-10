package com.thm.gw.dtos.service;

import com.thm.gw.entities.Company;
import com.thm.gw.entities.ServiceSubtype;
import com.thm.gw.entities.ServiceType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServiceDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        int duration,
        LocalDateTime createdAt,
        Boolean isActive,
        ServiceType typeId,
        ServiceSubtype subtypeId,
        Company companyId
) { }
