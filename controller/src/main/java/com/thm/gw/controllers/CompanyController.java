package com.thm.gw.controllers;

import com.thm.gw.dtos.company.CompanyDTO;
import com.thm.gw.forms.company.CompanyForm;
import com.thm.gw.services.ICompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/companies")
public class CompanyController {

    private final ICompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<CompanyDTO> companies = companyService.getAll();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody @Valid CompanyForm form) {
        return ResponseEntity.ok(companyService.addCompany(form));
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyDTO> updateCompany(
            @PathVariable Long id,
            @RequestBody @Valid CompanyForm form
    ) {
        return ResponseEntity.ok(companyService.updateCompany(id, form));
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyDTO> deleteCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }
}
