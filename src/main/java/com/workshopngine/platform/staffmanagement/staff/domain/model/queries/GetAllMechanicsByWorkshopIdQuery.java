package com.workshopngine.platform.staffmanagement.staff.domain.model.queries;

public record GetAllMechanicsByWorkshopIdQuery(Long workshopId) {
    public GetAllMechanicsByWorkshopIdQuery {
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("workshopId must be a positive number");
        }
    }
}
