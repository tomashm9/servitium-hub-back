package com.thm.gw.forms.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ServiceForm(

        @NotBlank(message = "Name is mandatory")
        String name,

        @NotBlank(message = "Description is mandatory")
        String description,

        @NotNull(message = "Price is mandatory")
        @Positive(message = "Price must be positive")
        BigDecimal price,

        @Min(value = 1, message = "Duration must be at least 1 minute")
        int duration,

        @NotNull(message = "Type ID is mandatory")
        Long typeId,

        @NotNull(message = "Subtype ID is mandatory")
        Long subtypeId,

        @NotNull(message = "Company ID is mandatory")
        Long companyId
) {}
