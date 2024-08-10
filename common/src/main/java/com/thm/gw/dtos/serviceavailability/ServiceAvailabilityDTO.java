package com.thm.gw.dtos.serviceavailability;

import java.time.LocalDate;
import java.time.LocalTime;

public record ServiceAvailabilityDTO(
        Long id,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        boolean isBooked,
        Long serviceId
) {}
