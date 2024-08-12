package com.thm.gw.dtos.openinghour;

import java.time.LocalTime;

public record OpeningHoursDTO(
        Long id,
        Long companyLocationId,
        String dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) {}
