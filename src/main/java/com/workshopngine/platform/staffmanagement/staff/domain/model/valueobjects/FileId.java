package com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FileId(Long fileId) {
    public FileId {
        if (fileId == null || fileId <= 0) {
            throw new IllegalArgumentException("FileId must be greater than 0");
        }
    }
}
