package com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record WorkshopId(String workshopId) {
    public WorkshopId {
        if (workshopId == null || workshopId.isBlank()) {
            throw new IllegalArgumentException("WorkshopId cannot be null or empty");
        }
    }
}
