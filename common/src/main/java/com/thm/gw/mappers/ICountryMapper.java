package com.thm.gw.mappers;

import com.thm.gw.dtos.country.CountryDTO;
import com.thm.gw.entities.Country;
import com.thm.gw.forms.country.CountryForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ICountryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Country toEntity(CountryForm countryForm);

    CountryDTO fromEntity(Country country);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(CountryForm countryFormUpdate, @MappingTarget Country country);
}
