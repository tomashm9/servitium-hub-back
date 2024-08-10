package com.thm.gw.services.impls;

import com.thm.gw.dtos.company.CompanyDTO;
import com.thm.gw.dtos.company.PagedCompaniesDTO;
import com.thm.gw.dtos.companylocation.CompanyLocationDTO;
import com.thm.gw.entities.Company;
import com.thm.gw.entities.CompanyLocation;
import com.thm.gw.entities.Owner;
import com.thm.gw.exceptions.NotAllowedException;
import com.thm.gw.exceptions.company.CompanyNotFoundException;
import com.thm.gw.forms.company.CompanyForm;
import com.thm.gw.mappers.ICompanyLocationMapper;
import com.thm.gw.mappers.ICompanyMapper;
import com.thm.gw.repositories.ICompanyLocationRepository;
import com.thm.gw.repositories.ICompanyRepository;
import com.thm.gw.repositories.IOwnerRepository;
import com.thm.gw.services.ICompanyService;
import com.thm.gw.specifications.CompanySpecifications;
import com.thm.gw.utils.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository companyRepository;
    private final ICompanyLocationRepository companyLocationRepository;
    private final IOwnerRepository ownerRepository;
    private final ICompanyMapper companyMapper;
    private final ICompanyLocationMapper companyLocationMapper;
    private final AuthServiceImpl authService;

    @Override
    public PagedCompaniesDTO getCompaniesByFilters(Map<String, String> filters, int page) {
        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE);

        Specification<Company> spec = CompanySpecifications.filterByFilters(filters);

        Page<Company> pagedCompanies = companyRepository.findAll(spec, pageable);

        return companyMapper.fromPage(pagedCompanies);
    }

    @Override
    public List<CompanyDTO> getAll() {
        return companyRepository.findAll().stream()
                .map(companyMapper::fromEntity)
                .toList();
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyMapper::fromEntity)
                .orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public List<CompanyLocationDTO> getCompanyLocations(Long companyId) {
        List<CompanyLocation> locations = companyLocationRepository.findAllByCompanyId(companyId);
        return locations.stream()
                .map(companyLocationMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CompanyDTO addCompany(CompanyForm form) {
        Owner currentUser = authService.getAuthenticatedOwner();
        Company company = companyMapper.toEntity(form);
        company = companyRepository.save(company);
        currentUser.setCompany(company);
        ownerRepository.save(currentUser);
        return companyMapper.fromEntity(company);
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyForm form) {
        Owner currentUser = authService.getAuthenticatedOwner();
        Company company = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);

        boolean isOwnerActive = ownerRepository
                .findByCompanyAndOwnerIdAndIsActive(company.getId(), currentUser.getId())
                .isPresent();

        if (!isOwnerActive) {
            throw new NotAllowedException("You are not allowed to update this company");
        }

        companyMapper.updateEntity(form, company);
        return companyMapper.fromEntity(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyDTO deleteCompany(Long id) {
        Owner currentUser = authService.getAuthenticatedOwner();
        Company company = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);

        boolean isOwnerActive = ownerRepository
                .findByCompanyAndOwnerIdAndIsActive(company.getId(), currentUser.getId())
                .isPresent();

        if (!isOwnerActive) {
            throw new NotAllowedException("You are not allowed to delete this company");
        }

        List<Long> ownersIds = ownerRepository.findAllOwnersIdsByCompany(company.getId());

        ownerRepository.updateAllActiveByIds(ownersIds, false);

        company.setActive(false);

        companyRepository.delete(company);

        return companyMapper.fromEntity(company);
    }


    @Override
    @Transactional
    public CompanyDTO addCompanyAsAdmin(Long userId, CompanyForm companyForm) {

        Company company = new Company();
        companyMapper.updateEntity(companyForm, company);
        companyRepository.save(company);

        Owner owner = new Owner(company);
        ownerRepository.save(owner);

        return companyMapper.fromEntity(company);
    }

    @Override
    @Transactional
    public CompanyDTO updateCompanyAsAdmin(Long id, CompanyForm companyForm) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        companyMapper.updateEntity(companyForm, company);

        return companyMapper.fromEntity(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyDTO deleteCompanyAsAdmin(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        List<Long> agentIds = ownerRepository.findAllOwnersIdsByCompany(company.getId());

        ownerRepository.updateAllActiveByIds(agentIds, false);

        company.setActive(false);

        return companyMapper.fromEntity(companyRepository.save(company));
    }

    @Override
    public CompanyDTO triggerActive(Long id, boolean isActive) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        if (isActive == company.isActive()) {
            throw new NotAllowedException(String.format("Company field 'isActive' already defined to '%s'", isActive));
        }

        company.setActive(isActive);

        return companyMapper.fromEntity(companyRepository.save(company));
    }
}