package com.thm.gw.services;

import com.thm.gw.dtos.companyimage.CompanyImageDTO;
import com.thm.gw.forms.companyimage.CompanyImageForm;

import java.util.List;

/**
 * Service interface for managing company images.
 */
public interface ICompanyImageService {

    /**
     * Adds a new image for a company.
     * @param companyId The ID of the company to which the image will be added.
     * @param companyImageForm  The {@link CompanyImageForm} containing the image data to be added.
     * @return The DTO representing the added image.
     */
    CompanyImageDTO addImage(Long companyId, CompanyImageForm companyImageForm);

    /**
     * Retrieves a list of images for a specific company.
     * @param companyId The ID of the company whose images are to be retrieved.
     * @return A list of DTOs representing the images of the company.
     */
    List<CompanyImageDTO> getImagesByCompanyId(Long companyId);

    /**
     * Deletes a specific image.
     * @param imageId The ID of the image to be deleted.
     */
    CompanyImageDTO deleteImage(Long imageId);
}
