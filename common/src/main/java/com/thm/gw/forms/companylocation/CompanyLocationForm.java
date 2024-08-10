package com.thm.gw.forms.companylocation;

import jakarta.validation.constraints.NotNull;

public record CompanyLocationForm(

        @NotNull(message = "Company ID cannot be null")
        Long companyId,

        @NotNull(message = "Country ID cannot be null")
        Long countryId,

        @NotNull(message = "City ID cannot be null")
        Long cityId,

        @NotNull(message = "Postal code ID cannot be null")
        Long postalCodeId
) {}
