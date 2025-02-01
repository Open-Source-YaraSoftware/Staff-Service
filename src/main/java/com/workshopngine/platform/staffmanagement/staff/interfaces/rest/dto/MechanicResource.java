package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto;

public record MechanicResource(
        Long id,
        Long workerId,
        String availabilityStatus,
        String operationalStatus
) {
}
