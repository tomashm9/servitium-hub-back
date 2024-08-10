package com.thm.gw.forms.reservation;

import com.thm.gw.enums.ReservationStatus;
import jakarta.validation.constraints.NotNull;

public record ReservationStatusForm(

        @NotNull(message = "Status is mandatory")
        ReservationStatus status
) { }
