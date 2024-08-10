package com.thm.gw.mappers;

import com.thm.gw.dtos.openinghour.OpeningHoursDTO;
import com.thm.gw.entities.OpeningHours;
import com.thm.gw.forms.openinghour.OpeningHoursForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IOpeningHoursMapper {

    @Mapping(source = "company.id", target = "companyId")
    OpeningHoursDTO fromEntity(OpeningHours openingHours);

    @Mapping(source = "companyId", target = "company.id")
    OpeningHours toEntity(OpeningHoursForm form);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "companyId", target = "company.id")
    void updateEntityFromForm(OpeningHoursForm form, @MappingTarget OpeningHours openingHours);
}
