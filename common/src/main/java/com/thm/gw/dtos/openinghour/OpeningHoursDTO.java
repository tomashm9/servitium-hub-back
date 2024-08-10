package com.thm.gw.dtos.openinghour;

import java.time.LocalTime;

public record OpeningHoursDTO(
        Long id,
        Long companyId,
        String dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) {}
