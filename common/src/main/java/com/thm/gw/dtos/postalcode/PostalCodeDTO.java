package com.thm.gw.dtos.postalcode;

public record PostalCodeDTO(
        Long id,
        String code1,
        String code2,
        Long cityId
) {}
