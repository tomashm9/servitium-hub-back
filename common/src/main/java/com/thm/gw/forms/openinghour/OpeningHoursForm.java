package com.thm.gw.forms.openinghour;

import java.time.LocalTime;

public record OpeningHoursForm(
        Long companyId,
        String dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) {}
