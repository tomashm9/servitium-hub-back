package com.thm.gw.controllers;

import com.thm.gw.dtos.reservation.ReservationDTO;
import com.thm.gw.forms.reservation.ReservationForm;
import com.thm.gw.forms.reservation.ReservationUpdateForm;
import com.thm.gw.services.IReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reservations")
@PreAuthorize("hasAuthority('CLIENT')")
public class ReservationController {

    private final IReservationService reservationService;

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationByIdAndClientId(id));
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @GetMapping("/service/{id:^[0-9]+$}")
    public ResponseEntity<List<ReservationDTO>> getAllByServiceId(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getAllByServiceId(id));
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody @Valid ReservationForm form) {
        return ResponseEntity.ok(reservationService.addReservation(form));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ReservationDTO> updateReservation(
            @PathVariable Long id,
            @RequestBody @Valid ReservationUpdateForm form
    ) {
        return ResponseEntity.ok(reservationService.updateReservation(id, form));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ReservationDTO> deleteReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.deleteReservation(id));
    }

    @PatchMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.cancelReservation(id));
    }
}
