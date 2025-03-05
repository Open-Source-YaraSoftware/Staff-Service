package com.workshopngine.platform.staffmanagement.staff.domain.model.queries;

public record GetMechanicByIdQuery(String mechanicId) {
    public GetMechanicByIdQuery {
        if (mechanicId == null || mechanicId.isEmpty()) {
            throw new IllegalArgumentException("Mechanic id is required");
        }
    }
}
