package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms;

import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateMechanicCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.WorkshopId;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateMechanicResource;

public class CreateMechanicCommandFromResourceAssembler {
    public static CreateMechanicCommand toCommandFromResource(CreateMechanicResource resource) {
        return new CreateMechanicCommand(new WorkshopId(resource.workshopId()));
    }
}
