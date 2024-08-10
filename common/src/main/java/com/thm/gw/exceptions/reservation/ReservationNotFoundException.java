package com.thm.gw.exceptions.reservation;

import com.thm.gw.exceptions.NotFoundException;

public class ReservationNotFoundException extends NotFoundException {
    public ReservationNotFoundException() {
        super("Reservation Not Found");
    }
}
