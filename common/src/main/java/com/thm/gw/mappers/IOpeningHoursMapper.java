package com.thm.gw.mappers;

import com.thm.gw.dtos.openinghour.OpeningHoursDTO;
import com.thm.gw.entities.OpeningHours;
import com.thm.gw.forms.openinghour.OpeningHoursForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalTime;

@Mapper(componentModel = "spring")
public interface IOpeningHoursMapper {

    @Mapping(source = "companyLocation.id", target = "companyLocationId")
    @Mapping(source = "openingTime", target = "startTime")
    @Mapping(source = "closingTime", target = "endTime")
    OpeningHoursDTO fromEntity(OpeningHours openingHours);

    default String mapOpeningTime(String openingTime) {
        if ("Closed".equalsIgnoreCase(openingTime)) {
            return null;
        }
        return openingTime;
    }

    @Mapping(source = "companyLocationId", target = "companyLocation.id")
    @Mapping(source = "openingTime", target = "openingTime")
    @Mapping(source = "closingTime", target = "closingTime")
    OpeningHours toEntity(OpeningHoursForm form);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "companyLocationId", target = "companyLocation.id")
    @Mapping(source = "openingTime", target = "openingTime")
    @Mapping(source = "closingTime", target = "closingTime")
    void updateEntityFromForm(OpeningHoursForm form, @MappingTarget OpeningHours openingHours);

    default LocalTime mapTime(String time) {
        if ("Closed".equals(time)) {
            return null;
        }
        return LocalTime.parse(time);
    }
}
