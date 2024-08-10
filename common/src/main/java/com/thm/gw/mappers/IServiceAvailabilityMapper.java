package com.thm.gw.mappers;

import com.thm.gw.dtos.serviceavailability.ServiceAvailabilityDTO;
import com.thm.gw.entities.ServiceAvailability;
import com.thm.gw.forms.serviceavailability.ServiceAvailabilityForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IServiceAvailabilityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "date", target = "date")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "endTime", target = "endTime")
    @Mapping(source = "isBooked", target = "booked")
    @Mapping(source = "serviceId", target = "service.id")
    ServiceAvailability toEntity(ServiceAvailabilityForm form);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", source = "date")
    @Mapping(target = "startTime", source = "startTime")
    @Mapping(target = "endTime", source = "endTime")
    @Mapping(target = "isBooked", source = "booked")
    @Mapping(target = "serviceId", source = "service.id")
    ServiceAvailabilityDTO fromEntity(ServiceAvailability serviceAvailability);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "date", target = "date")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "endTime", target = "endTime")
    @Mapping(source = "isBooked", target = "booked")
    @Mapping(source = "serviceId", target = "service.id")
    void updateEntity(ServiceAvailabilityForm form, @MappingTarget ServiceAvailability serviceAvailability);


}
