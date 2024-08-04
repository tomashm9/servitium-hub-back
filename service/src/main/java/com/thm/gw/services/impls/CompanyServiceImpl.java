package com.thm.gw.services.impls;

import com.thm.gw.dtos.company.CompanyDTO;
import com.thm.gw.entities.Company;
import com.thm.gw.entities.Owner;
import com.thm.gw.exceptions.NotAllowedException;
import com.thm.gw.exceptions.company.CompanyNotFoundException;
import com.thm.gw.forms.company.CompanyForm;
import com.thm.gw.mappers.ICompanyMapper;
import com.thm.gw.repositories.ICompanyRepository;
import com.thm.gw.repositories.IOwnerRepository;
import com.thm.gw.services.ICompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository companyRepository;
    private final IOwnerRepository ownerRepository;
    private final ICompanyMapper companyMapper;
    private final AuthServiceImpl authService;

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

        if (!company.getOwners().contains(currentUser)) {
            throw new NotAllowedException("You are not allowed to update this company");
        }

        companyMapper.updateEntity(form, company);

        return companyMapper.fromEntity(companyRepository.save(company));
    }

    @Override
    public CompanyDTO deleteCompany(Long id) {
        Owner currentUser = authService.getAuthenticatedOwner();

        Company company = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);

        if (!company.getOwners().contains(currentUser)) {
            throw new NotAllowedException("You are not allowed to delete this company");
        }

        companyRepository.delete(company);

        return companyMapper.fromEntity(company);
    }

    @Override
    public CompanyDTO addCompanyAsAdmin(Long userId, CompanyForm companyForm) {

        Company company = new Company();
        companyMapper.updateEntity(companyForm, company);
        companyRepository.save(company);

        Owner owner = new Owner(company);
        ownerRepository.save(owner);

        return companyMapper.fromEntity(company);
    }

    @Override
    public CompanyDTO updateCompanyAsAdmin(Long id, CompanyForm companyForm) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        companyMapper.updateEntity(companyForm, company);

        return companyMapper.fromEntity(companyRepository.save(company));
    }

    @Override
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
