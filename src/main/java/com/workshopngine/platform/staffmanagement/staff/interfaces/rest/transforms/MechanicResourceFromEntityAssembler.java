package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms;

import com.workshopngine.platform.staffmanagement.staff.domain.model.aggregates.Mechanic;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.MechanicResource;

public class MechanicResourceFromEntityAssembler {
    public static MechanicResource toResourceFromEntity(Mechanic entity) {
        return new MechanicResource(
                entity.getId(),
                entity.getWorkshopId().workshopId(),
                entity.getAvailabilityStatus().toString(),
                entity.getOperationalStatus().toString()
        );
    }
}
