package com.thm.gw.services;

import com.thm.gw.dtos.reservation.ReservationDTO;
import com.thm.gw.forms.reservation.ReservationForm;
import com.thm.gw.forms.reservation.ReservationStatusForm;
import com.thm.gw.forms.reservation.ReservationUpdateForm;

import java.util.List;

public interface IReservationService {

    /**
     * Retrieves a list of all reservations by service.
     * @param id the unique identifier of the reservation.
     * @return a List of Reservation objects representing all reservations.
     */
    List<ReservationDTO> getAllByServiceId(Long id);

    /**
     * Retrieves an reservation by its unique identifier.
     * @param id the unique identifier of the reservation.
     * @return the Reservation object corresponding to the specified id.
     */
    ReservationDTO getReservationById(Long id);

    /**
     * Retrieves an reservation by its unique identifier and the client's id.
     * @param id the unique identifier of the reservation.
     * @return the Reservation object corresponding to the specified id.
     */
    ReservationDTO getReservationByIdAndClientId(Long id);

    /**
     * Adds a new reservation.
     * @param form the Reservation object to be added.
     * @return the added Reservation object.
     */
    ReservationDTO addReservation(ReservationForm form);

    /**
     * Updates an existing reservation.
     * @param id the unique identifier of the reservation to be updated.
     * @param form the Reservation object containing the updated information.
     * @return the updated Reservation object.
     */
    ReservationDTO updateReservation(Long id, ReservationUpdateForm form);

    /**
     * Deletes an reservation by its unique identifier.
     * @param id the unique identifier of the reservation to be deleted.
     */
    ReservationDTO deleteReservation(Long id);

    /**
     * Cancels an reservation by its unique identifier.
     * @param id the unique identifier of the reservation to be cancelled.
     */
    ReservationDTO cancelReservation(Long id);

    /**
     * Triggers the status of an reservation.
     * @param id the unique identifier of the reservation.
     * @param form the ReservationStatus object containing the new status.
     * @return the updated Reservation object.
     */
    ReservationDTO triggerReservationStatus(Long id, ReservationStatusForm form);
}
