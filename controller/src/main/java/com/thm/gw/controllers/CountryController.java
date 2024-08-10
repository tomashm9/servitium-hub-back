package com.thm.gw.controllers;

import com.thm.gw.dtos.country.CountryDTO;
import com.thm.gw.forms.country.CountryForm;
import com.thm.gw.services.ICountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/countries")
@RequiredArgsConstructor
public class CountryController {

    private final ICountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<CountryDTO> getCountryByName(@RequestParam String name) {
        return ResponseEntity.ok(countryService.getCountryByName(name));
    }

    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryForm countryForm) {
        return ResponseEntity.ok(countryService.addCountry(countryForm));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CountryDTO> updateCountry(@PathVariable Long id, @RequestBody CountryForm countryForm) {
        return ResponseEntity.ok(countryService.updateCountry(id, countryForm));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CountryDTO> deleteCountry(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.deleteCountry(id));
    }
}
