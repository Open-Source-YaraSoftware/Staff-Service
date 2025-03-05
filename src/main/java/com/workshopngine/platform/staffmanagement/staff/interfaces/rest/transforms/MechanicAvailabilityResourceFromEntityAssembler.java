package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms;

import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.MechanicAvailabilityResource;

import java.time.LocalDateTime;

public class MechanicAvailabilityResourceFromEntityAssembler {
    public static MechanicAvailabilityResource toResourceFromEntity(String mechanicId, Boolean available) {
        return new MechanicAvailabilityResource(
                mechanicId,
                available
        );
    }
}
