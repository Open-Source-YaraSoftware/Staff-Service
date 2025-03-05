package com.workshopngine.platform.staffmanagement.staff.domain.model.queries;

import java.time.LocalDateTime;

public record IsAvailableMechanicByIdAndRequestedTimeQuery(
        String mechanicId,
        LocalDateTime requestedTime
) {
}
