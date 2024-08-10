package com.thm.gw.controllers;

import com.thm.gw.dtos.service.ServiceSubtypeDTO;
import com.thm.gw.dtos.service.ServiceTypeDTO;
import com.thm.gw.entities.ServiceSubtype;
import com.thm.gw.entities.ServiceType;
import com.thm.gw.repositories.IServiceSubtypeRepository;
import com.thm.gw.repositories.IServiceTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/service-categories")
public class ServiceCategoryController {

    private final IServiceTypeRepository serviceTypeRepository;
    private final IServiceSubtypeRepository serviceSubtypeRepository;

    public ServiceCategoryController(IServiceTypeRepository serviceTypeRepository, IServiceSubtypeRepository serviceSubtypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.serviceSubtypeRepository = serviceSubtypeRepository;
    }

    @GetMapping("/types")
    public ResponseEntity<List<ServiceTypeDTO>> getAllServiceTypes() {
        List<ServiceType> serviceTypes = serviceTypeRepository.findAll();
        List<ServiceTypeDTO> serviceTypeDTOs = serviceTypes.stream()
                .map(type -> new ServiceTypeDTO(type.getId(), type.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(serviceTypeDTOs);
    }

    @GetMapping("/subtypes/{typeId:^[0-9]+$}")
    public ResponseEntity<List<ServiceSubtypeDTO>> getSubtypesByType(@PathVariable Long typeId) {
        List<ServiceSubtype> subtypes = serviceSubtypeRepository.findByTypeId(typeId);
        List<ServiceSubtypeDTO> subtypeDTOs = subtypes.stream()
                .map(subtype -> new ServiceSubtypeDTO(subtype.getId(), subtype.getName(), subtype.getType().getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(subtypeDTOs);
    }
}
