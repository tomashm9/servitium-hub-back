package com.thm.gw.services;

import com.thm.gw.dtos.city.CityDTO;
import com.thm.gw.forms.city.CityForm;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing city-related operations.
 */
public interface ICityService {

    /**
     * Retrieves all cities.
     * @return A list of {@link CityDTO} representing all cities.
     */
    List<CityDTO> getAllCities();

    /**
     * Retrieves a city by its ID.
     * @param id ID of the city to be retrieved.
     * @return An {@link Optional} containing the {@link CityDTO} if found, or an empty {@link Optional} if not found.
     */
    CityDTO getCityById(Long id);

    /**
     * Adds a new city.
     * @param cityForm {@link CityForm} representing the city to be added.
     * @return The created {@link CityDTO}.
     */
    CityDTO addCity(CityForm cityForm);

    /**
     * Updates an existing city.
     * @param id ID of the city to be updated.
     * @param cityForm {@link CityForm} with the updated data of the city.
     * @return An {@link Optional} containing the updated {@link CityDTO} if successful, or an empty {@link Optional} if the city is not found.
     */
    CityDTO updateCity(Long id, CityForm cityForm);

    /**
     * Deletes a city by its ID.
     * @param id ID of the city to be deleted.
     * @return An {@link Optional} containing the {@link CityDTO} of the deleted city if successful, or an empty {@link Optional} if the city was not found.
     */
    CityDTO deleteCity(Long id);
}
