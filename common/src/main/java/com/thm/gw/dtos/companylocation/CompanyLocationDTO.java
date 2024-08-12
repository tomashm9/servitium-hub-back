package com.thm.gw.dtos.companylocation;

import com.thm.gw.dtos.city.CityDTO;
import com.thm.gw.dtos.country.CountryDTO;
import com.thm.gw.dtos.postalcode.PostalCodeDTO;

public record CompanyLocationDTO(
        Long id,
        Long companyId,
        Long countryId,
        Long cityId,
        Long postalCodeId,
        String addressLine1,
        String addressLine2,
        CityDTO city,
        CountryDTO country,
        PostalCodeDTO postalCode
) {}

