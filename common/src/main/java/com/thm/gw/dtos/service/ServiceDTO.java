package com.thm.gw.dtos.service;

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
        Long typeId,
        Long subtypeId,
        Long companyId
) { }
