package com.thm.gw.controllers.admin;

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
@RequestMapping("/admin/v1/companies")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminCompanyController {

    private final ICompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @PostMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyDTO> addCompanyAsAdmin(
            @PathVariable Long id,
            @Valid CompanyForm form
    ) {
        return ResponseEntity.ok(companyService.addCompanyAsAdmin(id, form));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyDTO> updateCompanyAsAdmin(
            @PathVariable Long id,
            @RequestBody @Valid CompanyForm form
    ) {
        return ResponseEntity.ok(companyService.updateCompanyAsAdmin(id, form));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyDTO> deleteCompanyAsAdmin(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.deleteCompanyAsAdmin(id));
    }

    @PatchMapping("/{id:^[0-9]+$}/activate")
    public ResponseEntity<CompanyDTO> activateCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.triggerActive(id, true));
    }

    @PatchMapping("/{id:^[0-9]+$}/deactivate")
    public ResponseEntity<CompanyDTO> deactivateCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.triggerActive(id, false));
    }
}
