package com.thm.gw.mappers;

import com.thm.gw.dtos.company.CompanyDTO;
import com.thm.gw.dtos.company.PagedCompaniesDTO;
import com.thm.gw.dtos.companylocation.CompanyLocationDTO;
import com.thm.gw.entities.Company;
import com.thm.gw.entities.CompanyLocation;
import com.thm.gw.forms.company.CompanyForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {ICompanyLocationMapper.class})
public interface ICompanyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "owners", ignore = true)
    @Mapping(target = "companyLocations", ignore = true)
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "active", ignore = true)
    Company toEntity(CompanyForm form);

    @Mapping(source = "companyLocations", target = "locations")
    @Mapping(target = "services", source = "services")
    CompanyDTO fromEntity(Company company);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "owners", ignore = true)
    @Mapping(target = "companyLocations", ignore = true)
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateEntity(CompanyForm formUpdate, @MappingTarget Company company);

    List<CompanyLocationDTO> mapCompanyLocations(Set<CompanyLocation> companyLocations);

    @Mapping(source = "page.content", target = "companies", defaultExpression = "java(java.util.Collections.emptyList())")
    @Mapping(source = "page.size", target = "elementsPerPage")
    PagedCompaniesDTO fromPage(Page<Company> page);
}
