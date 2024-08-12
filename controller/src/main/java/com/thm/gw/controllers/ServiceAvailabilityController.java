package com.thm.gw.controllers;

import com.thm.gw.dtos.serviceavailability.ServiceAvailabilityDTO;
import com.thm.gw.forms.serviceavailability.ServiceAvailabilityForm;
import com.thm.gw.services.IServiceAvailabilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/service-availability")
public class ServiceAvailabilityController {

    private final IServiceAvailabilityService serviceAvailabilityService;

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ServiceAvailabilityDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceAvailabilityService.getById(id));
    }

    @GetMapping("/service/{serviceId}/date/{date}")
    public ResponseEntity<List<ServiceAvailabilityDTO>> getByServiceAndDate(
            @PathVariable Long serviceId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(serviceAvailabilityService.getByServiceAndDate(serviceId, date));
    }

    @GetMapping("/available")
    public ResponseEntity<List<ServiceAvailabilityDTO>> getAvailableSlots(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(serviceAvailabilityService.getAvailableSlots(startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<ServiceAvailabilityDTO> create(@RequestBody @Valid ServiceAvailabilityForm serviceAvailabilityForm) {
        return ResponseEntity.ok(serviceAvailabilityService.create(serviceAvailabilityForm));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ServiceAvailabilityDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ServiceAvailabilityForm serviceAvailabilityForm) {
        return ResponseEntity.ok(serviceAvailabilityService.update(id, serviceAvailabilityForm));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serviceAvailabilityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
