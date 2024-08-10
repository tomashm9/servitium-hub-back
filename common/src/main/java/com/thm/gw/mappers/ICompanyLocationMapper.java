package com.thm.gw.mappers;

import com.thm.gw.dtos.companylocation.CompanyLocationDTO;
import com.thm.gw.entities.*;
import com.thm.gw.forms.companylocation.CompanyLocationForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ICompanyLocationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "company", source = "company")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "postalCode", source = "postalCode")
    CompanyLocation toEntity(
            CompanyLocationForm companyLocationForm,
            Company company,
            Country country,
            City city,
            PostalCode postalCode
    );

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "countryId", source = "country.id")
    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "postalCodeId", source = "postalCode.id")
    CompanyLocationDTO fromEntity(CompanyLocation companyLocation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "company", source = "company")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "postalCode", source = "postalCode")
    void updateEntityFromForm(
            CompanyLocationForm companyLocationFormUpdate,
            @MappingTarget CompanyLocation companyLocation,
            Company company,
            Country country,
            City city,
            PostalCode postalCode
    );
}
