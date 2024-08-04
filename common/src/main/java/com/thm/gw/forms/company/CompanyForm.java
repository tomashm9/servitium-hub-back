package com.thm.gw.forms.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CompanyForm(

        @NotBlank(message = "The name of the company is required.")
        String name,

        @NotBlank(message = "The description of the company is required")
        String description,

        @NotBlank(message = "The website URL of the company is required.")
        String websiteUrl,

        @NotNull(message = "The establishment date of the company is required.")
        LocalDate establishmentDate,

        @NotBlank(message = "The contact name of the company is required.")
        String contactName,

        @NotBlank(message = "The contact phone number of the company is required.")
        String contactPhoneNumber
) {
}
