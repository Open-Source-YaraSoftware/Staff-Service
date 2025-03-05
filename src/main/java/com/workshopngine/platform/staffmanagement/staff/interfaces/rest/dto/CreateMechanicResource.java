package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto;

import lombok.Builder;

@Builder
public record CreateMechanicResource(
        String workshopId
) {
}
