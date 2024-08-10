package com.thm.gw.dtos.companylocation;

public record CompanyLocationDTO(
        Long id,
        Long companyId,
        Long countryId,
        Long cityId,
        Long postalCodeId
) {}

