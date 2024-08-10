package com.thm.gw.services.impls;

import com.thm.gw.dtos.companylocation.CompanyLocationDTO;
import com.thm.gw.entities.*;
import com.thm.gw.exceptions.companylocation.CompanyLocationNotFoundException;
import com.thm.gw.exceptions.company.CompanyNotFoundException;
import com.thm.gw.exceptions.country.CountryNotFoundException;
import com.thm.gw.exceptions.city.CityNotFoundException;
import com.thm.gw.exceptions.postalcode.PostalCodeNotFoundException;
import com.thm.gw.forms.companylocation.CompanyLocationForm;
import com.thm.gw.mappers.ICompanyLocationMapper;
import com.thm.gw.repositories.ICompanyLocationRepository;
import com.thm.gw.repositories.ICompanyRepository;
import com.thm.gw.repositories.ICountryRepository;
import com.thm.gw.repositories.ICityRepository;
import com.thm.gw.repositories.IPostalCodeRepository;
import com.thm.gw.services.IAuthService;
import com.thm.gw.services.ICompanyLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyLocationServiceImpl implements ICompanyLocationService {

    private final ICompanyLocationRepository companyLocationRepository;
    private final ICompanyRepository companyRepository;
    private final ICountryRepository countryRepository;
    private final ICityRepository cityRepository;
    private final IPostalCodeRepository postalCodeRepository;
    private final ICompanyLocationMapper companyLocationMapper;
    private final IAuthService authService;

    @Override
    public List<CompanyLocationDTO> getAll() {
        User user = authService.getAuthenticatedUser();
        return companyLocationRepository.findAllByCompanyId(user.getId())
                .stream()
                .map(companyLocationMapper::fromEntity)
                .toList();
    }

    @Override
    public CompanyLocationDTO getById(Long id) {
        CompanyLocation companyLocation = companyLocationRepository.findById(id)
                .orElseThrow(CompanyLocationNotFoundException::new);
        return companyLocationMapper.fromEntity(companyLocation);
    }

    @Override
    @Transactional
    public CompanyLocationDTO add(CompanyLocationForm form) {
        Company company = companyRepository.findById(form.companyId())
                .orElseThrow(CompanyNotFoundException::new);

        Country country = countryRepository.findById(form.countryId())
                .orElseThrow(CountryNotFoundException::new);

        City city = cityRepository.findById(form.cityId())
                .orElseThrow(CityNotFoundException::new);

        PostalCode postalCode = postalCodeRepository.findById(form.postalCodeId())
                .orElseThrow(PostalCodeNotFoundException::new);

        CompanyLocation companyLocation = companyLocationMapper.toEntity(form, company, country, city, postalCode);

        return companyLocationMapper.fromEntity(companyLocationRepository.save(companyLocation));
    }

    @Override
    @Transactional
    public CompanyLocationDTO update(Long id, CompanyLocationForm companyLocationForm) {
        CompanyLocation companyLocation = companyLocationRepository.findById(id)
                .orElseThrow(CompanyLocationNotFoundException::new);

        Company company = companyRepository.findById(companyLocationForm.companyId())
                .orElseThrow(CompanyNotFoundException::new);

        Country country = countryRepository.findById(companyLocationForm.countryId())
                .orElseThrow(CountryNotFoundException::new);

        City city = cityRepository.findById(companyLocationForm.cityId())
                .orElseThrow(CityNotFoundException::new);

        PostalCode postalCode = postalCodeRepository.findById(companyLocationForm.postalCodeId())
                .orElseThrow(PostalCodeNotFoundException::new);

        companyLocationMapper.updateEntityFromForm(companyLocationForm, companyLocation, company, country, city, postalCode);

        CompanyLocation updatedCompanyLocation = companyLocationRepository.save(companyLocation);
        return companyLocationMapper.fromEntity(updatedCompanyLocation);
    }

    @Override
    @Transactional
    public CompanyLocationDTO delete(Long id) {
        CompanyLocation companyLocation = companyLocationRepository.findById(id)
                .orElseThrow(CompanyLocationNotFoundException::new);

        companyLocationRepository.delete(companyLocation);

        return companyLocationMapper.fromEntity(companyLocation);
    }
}
