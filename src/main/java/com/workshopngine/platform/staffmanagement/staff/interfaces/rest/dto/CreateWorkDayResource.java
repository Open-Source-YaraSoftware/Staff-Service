package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record CreateWorkDayResource(
        String day,
        LocalTime startAt,
        LocalTime endAt
) {
}
