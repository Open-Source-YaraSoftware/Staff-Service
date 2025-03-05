package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto;

public record MechanicResource(
        String id,
        String workshopId,
        String availabilityStatus,
        String operationalStatus
) {
}
