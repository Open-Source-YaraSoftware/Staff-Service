package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto;

public record MechanicResource(
        String id,
        String workerId,
        String availabilityStatus,
        String operationalStatus
) {
}
