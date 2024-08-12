package com.thm.gw.mappers;

import com.thm.gw.dtos.postalcode.PostalCodeDTO;
import com.thm.gw.entities.PostalCode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPostalCodeMapper {

    PostalCodeDTO fromEntity(PostalCode postalCode);
}
