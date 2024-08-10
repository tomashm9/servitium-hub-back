package com.thm.gw.forms.reservation;

import com.thm.gw.enums.ReservationStatus;
import jakarta.validation.constraints.NotNull;

public record ReservationUpdateForm(

        @NotNull(message = "Reservation status cannot be null")
        ReservationStatus reservationStatus
) { }
