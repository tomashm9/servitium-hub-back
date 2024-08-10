package com.thm.gw.mappers;

import com.thm.gw.dtos.city.CityDTO;
import com.thm.gw.entities.City;
import com.thm.gw.forms.city.CityForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ICityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    City toEntity(CityForm cityForm);

    CityDTO fromEntity(City city);

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromForm(CityForm form, @MappingTarget City city);
}
