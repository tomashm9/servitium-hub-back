package com.thm.gw.controllers;

import com.thm.gw.dtos.companyimage.CompanyImageDTO;
import com.thm.gw.forms.companyimage.CompanyImageForm;
import com.thm.gw.services.ICompanyImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/companies/{companyId}/company-images")
public class CompanyImageController {

    private final ICompanyImageService companyImageService;

    @PostMapping
    public ResponseEntity<CompanyImageDTO> addImage(
            @PathVariable Long companyId,
            @RequestBody CompanyImageForm companyImageForm
    ) {
        CompanyImageDTO companyImageDTO = companyImageService.addImage(companyId, companyImageForm);
        return ResponseEntity.ok(companyImageDTO);
    }

    @GetMapping
    public ResponseEntity<List<CompanyImageDTO>> getImagesByCompanyId(@PathVariable Long companyId) {
        List<CompanyImageDTO> images = companyImageService.getImagesByCompanyId(companyId);
        return ResponseEntity.ok(images);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        companyImageService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }
}
