package com.thm.gw.services.impls;

import com.thm.gw.dtos.service.ServiceDTO;
import com.thm.gw.entities.*;
import com.thm.gw.exceptions.NotAllowedException;
import com.thm.gw.exceptions.Service.ServiceNotFoundException;
import com.thm.gw.exceptions.Service.ServiceSubtypeNotFoundException;
import com.thm.gw.exceptions.Service.ServiceTypeNotFoundException;
import com.thm.gw.exceptions.company.CompanyNotActiveException;
import com.thm.gw.exceptions.company.CompanyNotFoundException;
import com.thm.gw.forms.service.ServiceForm;
import com.thm.gw.mappers.IServiceMapper;
import com.thm.gw.repositories.ICompanyRepository;
import com.thm.gw.repositories.IServiceRepository;
import com.thm.gw.repositories.IServiceSubtypeRepository;
import com.thm.gw.repositories.IServiceTypeRepository;
import com.thm.gw.services.IServiceService;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements IServiceService {

    private final IServiceRepository serviceRepository;
    private final IServiceTypeRepository serviceTypeRepository;
    private final IServiceSubtypeRepository serviceSubtypeRepository;
    private final ICompanyRepository companyRepository;
    private final IServiceMapper serviceMapper;
    private final AuthServiceImpl authService;

    @Override
    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO> getAllServicesByCompany(Long id) {
        return serviceRepository.findByCompanyId(id).stream()
                .map(serviceMapper::fromEntity)
                .toList();
    }

    @Override
    public ServiceDTO getServiceById(Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(ServiceNotFoundException::new);

        return serviceMapper.fromEntity(service);
    }

    @Override
    @Transactional
    public ServiceDTO createService(ServiceForm form) {

        Owner currentUser = authService.getAuthenticatedOwner();

        if (!currentUser.getCompany().isActive()) {
            throw new CompanyNotActiveException();
        }

        ServiceType type = serviceTypeRepository.findById(form.typeId())
                .orElseThrow(ServiceTypeNotFoundException::new);

        ServiceSubtype subtype = serviceSubtypeRepository.findById(form.subtypeId())
                .orElseThrow(ServiceSubtypeNotFoundException::new);

        Company company = companyRepository.findById(form.companyId())
                .orElseThrow(CompanyNotFoundException::new);

        Service service = serviceMapper.toEntity(form, type, subtype, company);

        return serviceMapper.fromEntity(serviceRepository.save(service));
    }


    @Override
    @Transactional
    public ServiceDTO updateService(Long id, ServiceForm form) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(ServiceNotFoundException::new);

        ServiceType type = serviceTypeRepository.findById(form.typeId())
                .orElseThrow(ServiceTypeNotFoundException::new);

        ServiceSubtype subtype = serviceSubtypeRepository.findById(form.subtypeId())
                .orElseThrow(ServiceSubtypeNotFoundException::new);

        serviceMapper.updateEntityFromForm(form, type, subtype, service);

        return serviceMapper.fromEntity(serviceRepository.save(service));
    }

    @Override
    public ServiceDTO deleteService(Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(ServiceNotFoundException::new);

        serviceRepository.deleteById(id);

        return serviceMapper.fromEntity(service);
    }

    @Override
    public ServiceDTO triggerActive(Long id, boolean isActive) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(ServiceNotFoundException::new);

        if (isActive == service.isActive()) {
            throw new NotAllowedException(String.format("Service field 'isActive' already defined to '%s'", isActive));
        }

        service.setActive(isActive);

        return serviceMapper.fromEntity(serviceRepository.save(service));
    }
}
