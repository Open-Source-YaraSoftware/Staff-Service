package com.workshopngine.platform.staffmanagement.staff.domain.model.commands;

import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.EDay;

import java.time.LocalTime;

public record CreateWorkDayCommand(
        Long mechanicId,
        EDay day,
        LocalTime startAt,
        LocalTime endAt
) {
}
