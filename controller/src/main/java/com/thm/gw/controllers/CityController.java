package com.thm.gw.controllers;

import com.thm.gw.dtos.city.CityDTO;
import com.thm.gw.forms.city.CityForm;
import com.thm.gw.services.ICityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cities")
public class CityController {

    private final ICityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<CityDTO> createCity(@RequestBody @Valid CityForm cityForm) {
        return ResponseEntity.ok(cityService.addCity(cityForm));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Long id, @RequestBody @Valid CityForm cityForm) {
        return ResponseEntity.ok(cityService.updateCity(id, cityForm));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CityDTO> deleteCity(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.deleteCity(id));
    }
}
