package com.thm.gw.services.impls;

import com.thm.gw.dtos.city.CityDTO;
import com.thm.gw.entities.City;
import com.thm.gw.exceptions.city.CityAlreadyExistsException;
import com.thm.gw.exceptions.city.CityNotFoundException;
import com.thm.gw.forms.city.CityForm;
import com.thm.gw.mappers.ICityMapper;
import com.thm.gw.repositories.ICityRepository;
import com.thm.gw.services.ICityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements ICityService {

    private final ICityRepository cityRepository;
    private final ICityMapper cityMapper;

    @Override
    public List<CityDTO> getAllCities() {
        return cityRepository.findAll().stream()
                .map(cityMapper::fromEntity)
                .toList();
    }

    @Override
    public CityDTO getCityById(Long id) {
        City city = cityRepository.findById(id).orElseThrow(CityNotFoundException::new);

        return cityMapper.fromEntity(city);
    }

    @Override
    public CityDTO addCity(CityForm cityForm) {
        if (cityRepository.findByName(cityForm.name()).isPresent()) {
            throw new CityAlreadyExistsException();
        }

        City city = cityMapper.toEntity(cityForm);

        return cityMapper.fromEntity(cityRepository.save(city));
    }

    @Override
    public CityDTO updateCity(Long id, CityForm cityForm) {
        City existingCity = cityRepository.findById(id).orElseThrow(CityNotFoundException::new);

        if (cityRepository.findByName(cityForm.name()).isPresent()) {
            throw new CityAlreadyExistsException();
        }

        cityMapper.updateEntityFromForm(cityForm, existingCity);

        return cityMapper.fromEntity(cityRepository.save(existingCity));
    }

    @Override
    public CityDTO deleteCity(Long id) {
        City city = cityRepository.findById(id).orElseThrow(CityNotFoundException::new);

        cityRepository.delete(city);

        return cityMapper.fromEntity(city);
    }
}
