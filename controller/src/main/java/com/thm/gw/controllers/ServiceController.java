package com.thm.gw.controllers;

import com.thm.gw.dtos.service.ServiceDTO;
import com.thm.gw.forms.service.ServiceForm;
import com.thm.gw.services.IServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/services")
public class ServiceController {

    private final IServiceService serviceService;

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/companies/{id:^[0-9]+$}")
    public ResponseEntity<List<ServiceDTO>> getAllServicesByCompany(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getAllServicesByCompany(id));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getServiceById(id));
    }

    @PreAuthorize("hasAnyAuthority('OWNER', 'MANAGER')")
    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody @Valid ServiceForm form) {
        return ResponseEntity.ok(serviceService.createService(form));
    }

    @PreAuthorize("hasAnyAuthority('OWNER', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(
            @PathVariable Long id,
            @RequestBody @Valid ServiceForm form) {
        return ResponseEntity.ok(serviceService.updateService(id, form));
    }

    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceDTO> deleteService(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.deleteService(id));
    }
}
