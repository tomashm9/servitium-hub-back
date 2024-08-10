package com.thm.gw.controllers.admin;

import com.thm.gw.dtos.service.ServiceDTO;
import com.thm.gw.services.IServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/services")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminServiceController {

    private final IServiceService serviceService;

    @PatchMapping("/{id:^[0-9]+$}/activate")
    public ResponseEntity<ServiceDTO> activateJobOffer(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.triggerActive(id, true));
    }

    @PatchMapping("/{id:^[0-9]+$}/deactivate")
    public ResponseEntity<ServiceDTO> deactivateJobOffer(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.triggerActive(id, false));
    }
}
