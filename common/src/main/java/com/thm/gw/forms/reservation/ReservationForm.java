package com.thm.gw.forms.reservation;

import com.thm.gw.enums.ReservationStatus;
import java.time.LocalDateTime;

public record ReservationForm(
        String name,
        String description,
        LocalDateTime date,
        ReservationStatus status,
        Long clientId,
        Long serviceId
) {}
