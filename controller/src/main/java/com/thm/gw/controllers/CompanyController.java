package com.thm.gw.controllers;

import com.thm.gw.dtos.company.CompanyDTO;
import com.thm.gw.dtos.company.PagedCompaniesDTO;
import com.thm.gw.dtos.companyimage.CompanyImageDTO;
import com.thm.gw.dtos.companylocation.CompanyLocationDTO;
import com.thm.gw.forms.company.CompanyForm;
import com.thm.gw.services.ICompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/companies")
public class CompanyController {
    private final ICompanyService companyService;

    @GetMapping(params = "filter")
    public ResponseEntity<PagedCompaniesDTO> getCompaniesByFilters(
            @RequestParam Map<String, String> filters,
            @RequestParam(defaultValue = "0") int page
    ) {
        PagedCompaniesDTO pagedCompanies = companyService.getCompaniesByFilters(filters, page);
        return ResponseEntity.ok(pagedCompanies);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<CompanyDTO> companies = companyService.getAll();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @GetMapping("/{companyId}/images")
    public ResponseEntity<List<CompanyImageDTO>> getCompanyImages(@PathVariable Long companyId) {
        return ResponseEntity.ok(companyService.getCompanyImages(companyId));
    }

    @GetMapping("/{companyId}/locations")
    public ResponseEntity<List<CompanyLocationDTO>> getCompanyLocations(@PathVariable Long companyId) {
        return ResponseEntity.ok(companyService.getCompanyLocations(companyId));
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody @Valid CompanyForm form) {
        return ResponseEntity.ok(companyService.addCompany(form));
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(
            @PathVariable Long id,
            @RequestBody @Valid CompanyForm form
    ) {
        return ResponseEntity.ok(companyService.updateCompany(id, form));
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CompanyDTO> deleteCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }
}

