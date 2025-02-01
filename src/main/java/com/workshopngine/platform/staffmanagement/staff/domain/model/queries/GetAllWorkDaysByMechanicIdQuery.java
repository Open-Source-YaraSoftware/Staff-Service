package com.workshopngine.platform.staffmanagement.staff.domain.model.queries;

public record GetAllWorkDaysByMechanicIdQuery(Long mechanicId) {
    public GetAllWorkDaysByMechanicIdQuery {
        if (mechanicId == null || mechanicId <= 0) {
            throw new IllegalArgumentException("Invalid mechanicId");
        }
    }
}
