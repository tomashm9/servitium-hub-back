package com.thm.gw.services;

import com.thm.gw.dtos.serviceavailability.ServiceAvailabilityDTO;
import com.thm.gw.forms.serviceavailability.ServiceAvailabilityForm;
import com.thm.gw.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for managing service availability.
 */
public interface IServiceAvailabilityService {

    /**
     * Retrieves a service availability by its ID.
     * @param id The ID of the service availability.
     * @return A {@link ServiceAvailabilityDTO} representing the service availability.
     * @throws NotFoundException If no service availability is found with the specified ID.
     */
    ServiceAvailabilityDTO getById(Long id);

    /**
     * Retrieves the list of service availabilities for a specific service on a given date.
     * @param serviceId The ID of the service.
     * @param date The date to check availability.
     * @return A list of {@link ServiceAvailabilityDTO} representing the availabilities for the specified service and date.
     */
    List<ServiceAvailabilityDTO> getByServiceAndDate(Long serviceId, LocalDate date);

    /**
     * Retrieves a list of available time slots within a date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of {@link ServiceAvailabilityDTO} representing the available slots within the specified range.
     */
    List<ServiceAvailabilityDTO> getAvailableSlots(LocalDate startDate, LocalDate endDate);

    /**
     * Creates a new service availability.
     * @param serviceAvailabilityForm The {@link ServiceAvailabilityForm} containing information for the new service availability.
     * @return The {@link ServiceAvailabilityDTO} of the created service availability.
     */
    ServiceAvailabilityDTO create(ServiceAvailabilityForm serviceAvailabilityForm);

    /**
     * Updates an existing service availability.
     * @param id The ID of the service availability to be updated.
     * @param serviceAvailabilityForm The {@link ServiceAvailabilityForm} containing updated information for the service availability.
     * @return The {@link ServiceAvailabilityDTO} of the updated service availability.
     * @throws NotFoundException If no service availability is found with the specified ID.
     */
    ServiceAvailabilityDTO update(Long id, ServiceAvailabilityForm serviceAvailabilityForm);

    /**
     * Deletes a service availability.
     * @param id The ID of the service availability to be deleted.
     * @throws NotFoundException If no service availability is found with the specified ID.
     */
    ServiceAvailabilityDTO delete(Long id);
}
