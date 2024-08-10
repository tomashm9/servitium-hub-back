package com.thm.gw.forms.serviceavailability;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ServiceAvailabilityForm(

        @NotNull(message = "The date is required.")
        @FutureOrPresent(message = "The date must be in the present or future.")
        LocalDate date,

        @NotNull(message = "The start time is required.")
        LocalTime startTime,

        @NotNull(message = "The end time is required.")
        LocalTime endTime,

        @NotBlank(message = "The service name is required.")
        Long serviceId,

        boolean isBooked
) {}
