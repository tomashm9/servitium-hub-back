package com.thm.gw.mappers;

import com.thm.gw.dtos.company.CompanyDTO;
import com.thm.gw.entities.Company;
import com.thm.gw.forms.company.CompanyForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ICompanyMapper {

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Company toEntity(CompanyForm form);

    CompanyDTO fromEntity(Company company);

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(CompanyForm requestUpdate, @MappingTarget Company company);
}
