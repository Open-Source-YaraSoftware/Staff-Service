package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto;

public record MechanicAvailabilityResource(
        String mechanicId,
        Boolean available
) {
}