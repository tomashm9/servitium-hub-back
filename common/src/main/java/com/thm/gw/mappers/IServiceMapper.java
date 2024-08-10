package com.thm.gw.mappers;

import com.thm.gw.dtos.service.ServiceDTO;
import com.thm.gw.entities.Company;
import com.thm.gw.entities.Service;
import com.thm.gw.entities.ServiceSubtype;
import com.thm.gw.entities.ServiceType;
import com.thm.gw.forms.service.ServiceForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IServiceMapper {

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "name", source = "form.name")
    @Mapping(target = "description", source = "form.description")
    @Mapping(target = "price", source = "form.price")
    @Mapping(target = "duration", source = "form.duration")
    @Mapping(target = "type", source = "serviceType")
    @Mapping(target = "subtype", source = "serviceSubtype")
    @Mapping(target = "company", source = "company")
    Service toEntity(
            ServiceForm form,
            ServiceType serviceType,
            ServiceSubtype serviceSubtype,
            Company company
    );

    @Mapping(target = "typeId", ignore = true)
    @Mapping(target = "subtypeId", ignore = true)
    @Mapping(source = "active", target = "isActive")
    @Mapping(source = "company.active", target = "companyId.active")
    ServiceDTO fromEntity(Service service);

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "name", source = "form.name")
    @Mapping(target = "description", source = "form.description")
    @Mapping(target = "price", source = "form.price")
    @Mapping(target = "duration", source = "form.duration")
    @Mapping(target = "type", source = "serviceType")
    @Mapping(target = "subtype", source = "serviceSubtype")
    void updateEntityFromForm(ServiceForm form, ServiceType serviceType, ServiceSubtype serviceSubtype, @MappingTarget Service service);
}

