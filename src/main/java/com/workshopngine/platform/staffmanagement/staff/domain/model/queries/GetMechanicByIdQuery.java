package com.workshopngine.platform.staffmanagement.staff.domain.model.queries;

public record GetMechanicByIdQuery(Long mechanicId) {
    public GetMechanicByIdQuery {
        if (mechanicId == null || mechanicId <= 0) {
            throw new IllegalArgumentException("Invalid mechanic id");
        }
    }
}
