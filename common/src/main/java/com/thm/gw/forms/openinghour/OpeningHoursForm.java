package com.thm.gw.forms.openinghour;

public record OpeningHoursForm(
        Long companyLocationId,
        String dayOfWeek,
        String openingTime,
        String closingTime
) {}
