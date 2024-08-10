package com.thm.gw.controllers.admin;

import com.thm.gw.dtos.reservation.ReservationDTO;
import com.thm.gw.forms.reservation.ReservationStatusForm;
import com.thm.gw.services.IReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/reservations")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminReservationController {

    private final IReservationService reservationService;

    @GetMapping("{id:^[0-9]+$}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PatchMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ReservationDTO> triggerReservationStatus(
            @PathVariable Long id,
            @RequestBody @Valid ReservationStatusForm form
    ) {
        return ResponseEntity.ok(reservationService.triggerReservationStatus(id, form));
    }
}
