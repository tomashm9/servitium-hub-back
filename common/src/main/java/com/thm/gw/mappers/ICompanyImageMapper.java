package com.thm.gw.mappers;

import com.thm.gw.dtos.companyimage.CompanyImageDTO;
import com.thm.gw.entities.CompanyImage;
import com.thm.gw.forms.companyimage.CompanyImageForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ICompanyImageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(source = "imageUrl", target = "imageUrl")
    CompanyImage toEntity(CompanyImageForm form);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "imageUrl", target = "imageUrl")
    CompanyImageDTO fromEntity(CompanyImage companyImage);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(source = "imageUrl", target = "imageUrl")
    void updateEntityFromForm(CompanyImageForm form, @MappingTarget CompanyImage companyImage);
}
