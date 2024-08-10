package com.thm.gw.services.impls;

import com.thm.gw.dtos.openinghour.OpeningHoursDTO;
import com.thm.gw.entities.Company;
import com.thm.gw.entities.OpeningHours;
import com.thm.gw.exceptions.Service.ServiceNotFoundException;
import com.thm.gw.forms.openinghour.OpeningHoursForm;
import com.thm.gw.mappers.IOpeningHoursMapper;
import com.thm.gw.repositories.IOpeningHoursRepository;
import com.thm.gw.repositories.ICompanyRepository;
import com.thm.gw.services.IOpeningHoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpeningHoursServiceImpl implements IOpeningHoursService {

    private final IOpeningHoursRepository openingHoursRepository;
    private final ICompanyRepository companyRepository;
    private final IOpeningHoursMapper openingHoursMapper;

    @Override
    public List<OpeningHoursDTO> getAllByCompanyId(Long companyId) {
        return openingHoursRepository.findByCompanyId(companyId).stream()
                .map(openingHoursMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OpeningHoursDTO addOpeningHours(OpeningHoursForm form) {
        Company company = companyRepository.findById(form.companyId())
                .orElseThrow(ServiceNotFoundException::new);

        OpeningHours openingHours = openingHoursMapper.toEntity(form);
        openingHours.setCompany(company);

        return openingHoursMapper.fromEntity(openingHoursRepository.save(openingHours));
    }

    @Override
    @Transactional
    public OpeningHoursDTO updateOpeningHours(Long id, OpeningHoursForm form) {
        OpeningHours openingHours = openingHoursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opening hours not found"));

        openingHoursMapper.updateEntityFromForm(form, openingHours);
        return openingHoursMapper.fromEntity(openingHoursRepository.save(openingHours));
    }

    @Override
    @Transactional
    public void deleteOpeningHours(Long id) {
        openingHoursRepository.deleteById(id);
    }
}
