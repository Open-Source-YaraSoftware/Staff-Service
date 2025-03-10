package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto;

import java.time.LocalTime;

public record WorkDayResource(
        String id,
        String day,
        LocalTime startAt,
        LocalTime endAt
) {
}
