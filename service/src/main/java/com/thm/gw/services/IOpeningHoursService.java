package com.thm.gw.services;

import com.thm.gw.dtos.openinghour.OpeningHoursDTO;
import com.thm.gw.forms.openinghour.OpeningHoursForm;

import java.util.List;

public interface IOpeningHoursService {

    /**
     * Retrieves a list of all opening hours for a specific company location.
     * @param companyLocationId The unique identifier of the company location.
     * @return A list of OpeningHoursDTO objects representing the opening hours of the specified company location.
     */
    List<OpeningHoursDTO> getAllByCompanyLocationId(Long companyLocationId);

    /**
     * Adds new opening hours for a company.
     * @param form The OpeningHoursForm object containing the details of the opening hours to be added.
     * @return The OpeningHoursDTO object representing the added opening hours.
     */
    OpeningHoursDTO addOpeningHours(OpeningHoursForm form);

    /**
     * Updates the opening hours for a specific record.
     * @param id The unique identifier of the opening hours record to be updated.
     * @param form The OpeningHoursForm object containing the updated details.
     * @return The OpeningHoursDTO object representing the updated opening hours.
     */
    OpeningHoursDTO updateOpeningHours(Long id, OpeningHoursForm form);

    /**
     * Deletes the opening hours for a specific record.
     * @param id The unique identifier of the opening hours record to be deleted.
     */
    void deleteOpeningHours(Long id);
}
