package com.thm.gw.services;

import com.thm.gw.dtos.country.CountryDTO;
import com.thm.gw.exceptions.country.CountryNotFoundException;
import com.thm.gw.forms.country.CountryForm;

import java.util.List;

public interface ICountryService {

    /**
     * Retrieves a list of all available countries.
     *
     * @return A list of {@link CountryDTO} objects representing all countries.
     */
    List<CountryDTO> getAllCountries();

    /**
     * Retrieves a country based on its unique ID.
     *
     * @param id The ID of the country to retrieve.
     * @return A {@link CountryDTO} representing the country if found.
     * @throws CountryNotFoundException if no country is found with the provided ID.
     */
    CountryDTO getCountryById(Long id);

    /**
     * Retrieves a country based on its name.
     *
     * @param name The name of the country to retrieve.
     * @return A {@link CountryDTO} representing the country if found.
     * @throws CountryNotFoundException if no country is found with the provided name.
     */
    CountryDTO getCountryByName(String name);

    /**
     * Adds a new country to the system.
     *
     * @param form The {@link CountryForm} containing the details of the country to be added.
     * @return A {@link CountryDTO} representing the newly added country.
     */
    CountryDTO addCountry(CountryForm form);

    /**
     * Updates the details of an existing country.
     *
     * @param id   The ID of the country to update.
     * @param form The {@link CountryForm} containing the new details of the country.
     * @return A {@link CountryDTO} representing the updated country.
     * @throws CountryNotFoundException if no country is found with the provided ID.
     */
    CountryDTO updateCountry(Long id, CountryForm form);

    /**
     * Deletes a country based on its unique ID.
     *
     * @param id The ID of the country to delete.
     * @return A {@link CountryDTO} representing the deleted country.
     * @throws CountryNotFoundException if no country is found with the provided ID.
     */
    CountryDTO deleteCountry(Long id);
}