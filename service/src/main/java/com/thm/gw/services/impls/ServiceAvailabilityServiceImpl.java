package com.thm.gw.services.impls;

import com.thm.gw.dtos.serviceavailability.ServiceAvailabilityDTO;
import com.thm.gw.entities.*;
import com.thm.gw.exceptions.NotFoundException;
import com.thm.gw.forms.serviceavailability.ServiceAvailabilityForm;
import com.thm.gw.mappers.IServiceAvailabilityMapper;
import com.thm.gw.repositories.IServiceAvailabilityRepository;
import com.thm.gw.repositories.IServiceRepository;
import com.thm.gw.services.IServiceAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceAvailabilityServiceImpl implements IServiceAvailabilityService {

    private final IServiceAvailabilityRepository serviceAvailabilityRepository;
    private final IServiceAvailabilityMapper serviceAvailabilityMapper;
    private final IServiceRepository serviceRepository;

    @Override
    public ServiceAvailabilityDTO getById(Long id) {
        return serviceAvailabilityRepository.findById(id)
                .map(serviceAvailabilityMapper::fromEntity)
                .orElseThrow(() -> new NotFoundException("ServiceAvailability not found"));
    }

    @Override
    public List<ServiceAvailabilityDTO> getByServiceAndDate(Long serviceId, LocalDate date) {
        List<ServiceAvailability> availabilities = serviceAvailabilityRepository.findByServiceAndDate(serviceId, date);
        return availabilities.stream()
                .map(serviceAvailabilityMapper::fromEntity)
                .toList();
    }

    @Override
    public List<ServiceAvailabilityDTO> getAvailableSlots(LocalDate startDate, LocalDate endDate) {
        List<ServiceAvailability> availabilities = serviceAvailabilityRepository.findAvailableSlots(startDate, endDate);
        return availabilities.stream()
                .map(serviceAvailabilityMapper::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public ServiceAvailabilityDTO create(ServiceAvailabilityForm serviceAvailabilityForm) {
        Service service = serviceRepository.findById(serviceAvailabilityForm.serviceId())
                .orElseThrow(() -> new NotFoundException("Service not found"));

        ServiceAvailability serviceAvailability = serviceAvailabilityMapper.toEntity(serviceAvailabilityForm);
        serviceAvailability.setService(service);

        ServiceAvailability savedServiceAvailability = serviceAvailabilityRepository.save(serviceAvailability);
        return serviceAvailabilityMapper.fromEntity(savedServiceAvailability);
    }

    @Override
    @Transactional
    public ServiceAvailabilityDTO update(Long id, ServiceAvailabilityForm serviceAvailabilityForm) {
        ServiceAvailability existingAvailability = serviceAvailabilityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ServiceAvailability not found"));

        Service service = serviceRepository.findById(serviceAvailabilityForm.serviceId())
                .orElseThrow(() -> new NotFoundException("Service not found"));

        serviceAvailabilityMapper.updateEntity(serviceAvailabilityForm, existingAvailability);
        existingAvailability.setService(service);

        ServiceAvailability updatedServiceAvailability = serviceAvailabilityRepository.save(existingAvailability);
        return serviceAvailabilityMapper.fromEntity(updatedServiceAvailability);
    }

    @Override
    @Transactional
    public ServiceAvailabilityDTO delete(Long id) {
        ServiceAvailability serviceAvailability = serviceAvailabilityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ServiceAvailability not found"));

        serviceAvailabilityRepository.delete(serviceAvailability);

        return serviceAvailabilityMapper.fromEntity(serviceAvailability);
    }
}
