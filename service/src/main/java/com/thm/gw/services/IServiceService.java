package com.thm.gw.services;

import com.thm.gw.dtos.service.ServiceDTO;
import com.thm.gw.forms.service.ServiceForm;

import java.util.List;

/**
 * Interface for the service management service.
 */
public interface IServiceService {

    /*
     * Retrieves a list of all services.
     * @param params the parameters to filter the services.
     * @param page the page number to retrieve.
     * @return a paged response containing the services.

    PagedServicesDTO getAll(Map<String, String> params, int page);
     */

    /**
     * Retrieves all services.
     * @return List of services.
     */
    List<ServiceDTO> getAllServices();

    /**
     * Retrieves all services for a specified company.
     * @param id the identifier of the company.
     * @return a list of services for the specified company.
     */
    List<ServiceDTO> getAllServicesByCompany(Long id);

    /**
     * Retrieves a service by ID.
     * @param id The ID of the service.
     * @return The found service.
     */
    ServiceDTO getServiceById(Long id);

    /**
     * Creates a new service.
     * @param form The data of the new service.
     * @return The created service.
     */
    ServiceDTO createService(ServiceForm form);

    /**
     * Updates an existing service.
     * @param id   The ID of the service to be updated.
     * @param form The new data of the service.
     * @return The updated service.
     */
    ServiceDTO updateService(Long id, ServiceForm form);

    /**
     * Deletes a service by ID.
     * @param id The ID of the service to be deleted.
     */
    ServiceDTO deleteService(Long id);

    /**
     * Triggers the active status of a service.
     * @param id the identifier of the service to trigger.
     * @param isActive the new active status of the service.
     * @return the updated service.
     */
    ServiceDTO triggerActive(Long id, boolean isActive);
}
