package com.thm.gw.services;

import com.thm.gw.dtos.companylocation.CompanyLocationDTO;
import com.thm.gw.forms.companylocation.CompanyLocationForm;

import java.util.List;

/**
 * Service interface for managing company locations, providing methods for retrieving,
 * adding, updating, and deleting company location records.
 */
public interface ICompanyLocationService {

    /**
     * Retrieves a list of all registered company locations.
     *
     * @return A list of {@link CompanyLocationDTO} objects representing all company locations in the system.
     */
    List<CompanyLocationDTO> getAll();

    /**
     * Retrieves the details of a specific company location by its ID.
     *
     * @param id The ID of the company location to retrieve.
     * @return A {@link CompanyLocationDTO} object containing the details of the requested company location,
     *         or null if the location is not found.
     */
    CompanyLocationDTO getById(Long id);

    /**
     * Creates a new company location based on the provided details.
     *
     * @param companyLocationForm The {@link CompanyLocationForm} containing the data for the new company location.
     * @return A {@link CompanyLocationDTO} object representing the newly created company location.
     */
    CompanyLocationDTO add(CompanyLocationForm companyLocationForm);

    /**
     * Updates an existing company location with new information.
     *
     * @param id The ID of the company location to be updated.
     * @param companyLocationForm The {@link CompanyLocationForm} containing the updated data.
     * @return A {@link CompanyLocationDTO} object representing the updated company location.
     */
    CompanyLocationDTO update(Long id, CompanyLocationForm companyLocationForm);

    /**
     * Deletes a company location identified by its ID.
     *
     * @param id The ID of the company location to delete.
     * @return A {@link CompanyLocationDTO} object representing the deleted company location.
     */
    CompanyLocationDTO delete(Long id);
}
