package com.thm.gw.forms.country;

import jakarta.validation.constraints.NotBlank;

public record CountryForm (

        @NotBlank(message = "The name of the country is required.")
        String name
) { }
