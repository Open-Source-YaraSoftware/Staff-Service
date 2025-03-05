package com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FileId(String fileId) {
    public FileId {
        if (fileId == null || fileId.isBlank()) {
            throw new IllegalArgumentException("fileId cannot be null or blank");
        }
    }
}
