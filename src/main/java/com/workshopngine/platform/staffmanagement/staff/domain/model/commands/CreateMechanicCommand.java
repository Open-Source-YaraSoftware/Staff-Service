package com.workshopngine.platform.staffmanagement.staff.domain.model.commands;

import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.WorkshopId;

public record CreateMechanicCommand(
        WorkshopId workshopId
) {
}
