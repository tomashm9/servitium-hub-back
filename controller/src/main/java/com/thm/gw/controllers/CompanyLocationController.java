package com.thm.gw.controllers;

import com.thm.gw.dtos.companylocation.CompanyLocationDTO;
import com.thm.gw.forms.companylocation.CompanyLocationForm;
import com.thm.gw.services.ICompanyLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/company-locations")
@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
public class CompanyLocationController {

    private final ICompanyLocationService companyLocationService;

    @GetMapping
    public ResponseEntity<List<CompanyLocationDTO>> getAllCompanyLocations() {
        return ResponseEntity.ok(companyLocationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyLocationDTO> getCompanyLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(companyLocationService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyLocationDTO> createCompanyLocation(@RequestBody CompanyLocationForm companyLocationForm) {
        return ResponseEntity.ok(companyLocationService.add(companyLocationForm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyLocationDTO> updateCompanyLocation(@PathVariable Long id, @RequestBody CompanyLocationForm companyLocationForm) {
        return ResponseEntity.ok(companyLocationService.update(id, companyLocationForm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CompanyLocationDTO> deleteCompanyLocation(@PathVariable Long id) {
        return ResponseEntity.ok(companyLocationService.delete(id));
    }
}
