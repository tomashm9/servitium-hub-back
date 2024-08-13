package com.thm.gw.dtos.company;

import com.thm.gw.dtos.companyimage.CompanyImageDTO;
import com.thm.gw.dtos.companylocation.CompanyLocationDTO;
import com.thm.gw.dtos.service.ServiceDTO;

import java.util.List;

public record CompanyDTO(
        Long id,
        String name,
        String description,
        String contactName,
        String contactPhoneNumber,
        String websiteUrl,
        List<CompanyLocationDTO> locations,
        List<CompanyImageDTO> images,
        List<ServiceDTO> services
) { }
