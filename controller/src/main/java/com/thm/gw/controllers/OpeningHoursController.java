package com.thm.gw.controllers;

import com.thm.gw.dtos.openinghour.OpeningHoursDTO;
import com.thm.gw.forms.openinghour.OpeningHoursForm;
import com.thm.gw.services.IOpeningHoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opening-hours")
@RequiredArgsConstructor
public class OpeningHoursController {

    private final IOpeningHoursService openingHoursService;

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<OpeningHoursDTO>> getOpeningHoursByCompanyId(@PathVariable Long companyId) {
        return ResponseEntity.ok(openingHoursService.getAllByCompanyId(companyId));
    }

    @PostMapping
    public ResponseEntity<OpeningHoursDTO> addOpeningHours(@RequestBody OpeningHoursForm form) {
        return ResponseEntity.ok(openingHoursService.addOpeningHours(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpeningHoursDTO> updateOpeningHours(@PathVariable Long id, @RequestBody OpeningHoursForm form) {
        return ResponseEntity.ok(openingHoursService.updateOpeningHours(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOpeningHours(@PathVariable Long id) {
        openingHoursService.deleteOpeningHours(id);
        return ResponseEntity.noContent().build();
    }
}
