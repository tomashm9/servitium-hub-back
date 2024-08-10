package com.thm.gw.services.impls;

import com.thm.gw.dtos.country.CountryDTO;
import com.thm.gw.entities.Country;
import com.thm.gw.exceptions.country.CountryNotFoundException;
import com.thm.gw.forms.country.CountryForm;
import com.thm.gw.mappers.ICountryMapper;
import com.thm.gw.repositories.ICountryRepository;
import com.thm.gw.services.ICountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements ICountryService {

    private final ICountryRepository countryRepository;
    private final ICountryMapper countryMapper;

    @Override
    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::fromEntity)
                .toList();
    }

    @Override
    public CountryDTO getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(CountryNotFoundException::new);
        return countryMapper.fromEntity(country);
    }

    @Override
    public CountryDTO getCountryByName(String name) {
        Country country = countryRepository.findByName(name)
                .orElseThrow(CountryNotFoundException::new);
        return countryMapper.fromEntity(country);
    }

    @Override
    public CountryDTO addCountry(CountryForm form) {
        if (countryRepository.findByName(form.name()).isPresent()) {
            throw new CountryNotFoundException();
        }
        Country country = countryMapper.toEntity(form);
        return countryMapper.fromEntity(countryRepository.save(country));
    }

    @Override
    public CountryDTO updateCountry(Long id, CountryForm form) {
        Country existingCountry = countryRepository.findById(id)
                .orElseThrow(CountryNotFoundException::new);
        countryMapper.updateEntity(form, existingCountry);
        return countryMapper.fromEntity(existingCountry);
    }

    @Override
    public CountryDTO deleteCountry(Long id) {
        Country existingCountry = countryRepository.findById(id)
                .orElseThrow(CountryNotFoundException::new);
        countryRepository.delete(existingCountry);
        return countryMapper.fromEntity(existingCountry);
    }
}