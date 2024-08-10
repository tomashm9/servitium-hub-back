package com.thm.gw.forms.city;

import com.thm.gw.entities.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CityForm (
        @NotBlank(message = "Name is mandatory")
        String name,

        @NotNull(message = "Country is mandatory")
        Country country
) { }
