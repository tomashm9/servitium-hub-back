package com.thm.gw.controllers;

import com.thm.gw.dtos.openinghour.OpeningHoursDTO;
import com.thm.gw.forms.openinghour.OpeningHoursForm;
import com.thm.gw.services.IOpeningHoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/opening-hours")
@RequiredArgsConstructor
public class OpeningHoursController {

    private final IOpeningHoursService openingHoursService;

    @GetMapping("/location/{companyLocationId}")
    public ResponseEntity<List<OpeningHoursDTO>> getOpeningHoursByCompanyLocationId(@PathVariable Long companyLocationId) {
        List<OpeningHoursDTO> openingHours = openingHoursService.getAllByCompanyLocationId(companyLocationId);
        return ResponseEntity.ok(openingHours);
    }

    @PostMapping
    public ResponseEntity<OpeningHoursDTO> addOpeningHours(@RequestBody OpeningHoursForm form) {
        OpeningHoursDTO createdOpeningHours = openingHoursService.addOpeningHours(form);
        return ResponseEntity.ok(createdOpeningHours);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpeningHoursDTO> updateOpeningHours(@PathVariable Long id, @RequestBody OpeningHoursForm form) {
        OpeningHoursDTO updatedOpeningHours = openingHoursService.updateOpeningHours(id, form);
        return ResponseEntity.ok(updatedOpeningHours);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOpeningHours(@PathVariable Long id) {
        openingHoursService.deleteOpeningHours(id);
        return ResponseEntity.noContent().build();
    }
}
