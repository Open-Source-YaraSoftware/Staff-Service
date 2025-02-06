package com.workshopngine.platform.staffmanagement.staff.domain.model.queries;

public record GetAllWorkDaysByMechanicIdQuery(String mechanicId) {
    public GetAllWorkDaysByMechanicIdQuery {
        if (mechanicId == null || mechanicId.isEmpty()) {
            throw new IllegalArgumentException("Mechanic id is required");
        }
    }
}
