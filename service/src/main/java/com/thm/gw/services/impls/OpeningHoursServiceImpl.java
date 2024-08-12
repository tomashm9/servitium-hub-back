package com.thm.gw.services.impls;

import com.thm.gw.dtos.openinghour.OpeningHoursDTO;
import com.thm.gw.entities.CompanyLocation;
import com.thm.gw.entities.OpeningHours;
import com.thm.gw.exceptions.Service.ServiceNotFoundException;
import com.thm.gw.forms.openinghour.OpeningHoursForm;
import com.thm.gw.mappers.IOpeningHoursMapper;
import com.thm.gw.repositories.ICompanyLocationRepository;
import com.thm.gw.repositories.IOpeningHoursRepository;
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
    private final ICompanyLocationRepository companyLocationRepository;
    private final IOpeningHoursMapper openingHoursMapper;

    @Override
    public List<OpeningHoursDTO> getAllByCompanyLocationId(Long companyLocationId) {
        return openingHoursRepository.findByCompanyLocationId(companyLocationId).stream()
                .map(openingHoursMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OpeningHoursDTO addOpeningHours(OpeningHoursForm form) {
        CompanyLocation companyLocation = companyLocationRepository.findById(form.companyLocationId())
                .orElseThrow(() -> new ServiceNotFoundException("CompanyLocation not found"));

        OpeningHours openingHours = openingHoursMapper.toEntity(form);
        openingHours.setCompanyLocation(companyLocation);

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
