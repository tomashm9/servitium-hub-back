package com.thm.gw.dtos.reservation;

import com.thm.gw.enums.ReservationStatus;
import java.time.LocalDateTime;

public record ReservationDTO(
        Long id,
        String name,
        String description,
        LocalDateTime date,
        LocalDateTime createdAt,
        ReservationStatus status,
        Long clientId,
        Long serviceId
) {}
