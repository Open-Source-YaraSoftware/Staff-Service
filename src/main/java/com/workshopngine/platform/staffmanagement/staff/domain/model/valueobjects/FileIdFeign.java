package com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects;

public record FileIdFeign(String id) {
    public FileIdFeign {
        if (id == null) {
            throw new IllegalArgumentException("FileId cannot be null");
        }
    }
}
