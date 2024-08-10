package com.thm.gw.exceptions.reservation;

import com.thm.gw.enums.ReservationStatus;
import com.thm.gw.exceptions.NotAllowedException;

public class ReservationStatusAlreadyDefinedException extends NotAllowedException {
    public ReservationStatusAlreadyDefinedException(ReservationStatus status) {
        super(String.format("The status %s is already defined", status));
    }
}
