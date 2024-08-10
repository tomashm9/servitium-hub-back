package com.thm.gw.services.impls;

import com.thm.gw.dtos.companyimage.CompanyImageDTO;
import com.thm.gw.exceptions.companyimage.CompanyImageNotFoundException;
import com.thm.gw.forms.companyimage.CompanyImageForm;
import com.thm.gw.entities.Company;
import com.thm.gw.entities.CompanyImage;
import com.thm.gw.exceptions.company.CompanyNotFoundException;
import com.thm.gw.mappers.ICompanyImageMapper;
import com.thm.gw.repositories.ICompanyImageRepository;
import com.thm.gw.repositories.ICompanyRepository;
import com.thm.gw.services.ICompanyImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyImageServiceImpl implements ICompanyImageService {

    private final ICompanyRepository companyRepository;
    private final ICompanyImageRepository companyImageRepository;
    private final ICompanyImageMapper companyImageMapper;

    @Override
    public List<CompanyImageDTO> getImagesByCompanyId(Long companyId) {
        List<CompanyImage> images = companyImageRepository.findByCompanyId(companyId);
        return images.stream()
                .map(image -> new CompanyImageDTO(image.getId(), image.getImageUrl()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CompanyImageDTO addImage(Long companyId, CompanyImageForm companyImageForm) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);
        CompanyImage companyImage = new CompanyImage();
        companyImage.setCompany(company);
        companyImage.setImageUrl(companyImageForm.imageUrl());
        companyImage = companyImageRepository.save(companyImage);
        return new CompanyImageDTO(companyImage.getId(), companyImage.getImageUrl());
    }

    @Override
    @Transactional
    public CompanyImageDTO deleteImage(Long imageId) {
        CompanyImage companyImage = companyImageRepository.findById(imageId)
                        .orElseThrow(CompanyImageNotFoundException::new);

        companyImageRepository.deleteById(imageId);

        return companyImageMapper.fromEntity(companyImage);
    }
}
