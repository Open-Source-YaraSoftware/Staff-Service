package com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record WorkshopId(Long workshopId) {
    public WorkshopId {
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("WorkshopId must be a positive number");
        }
    }
}
