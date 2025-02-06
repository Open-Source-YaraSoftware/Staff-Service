package com.workshopngine.platform.staffmanagement.staff.domain.model.queries;

public record GetAllMechanicsByWorkshopIdQuery(String workshopId) {
    public GetAllMechanicsByWorkshopIdQuery {
        if (workshopId == null || workshopId.isBlank()) {
            throw new IllegalArgumentException("workshopId cannot be null or empty");
        }
    }
}
