package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms;

import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.WorkDay;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.WorkDayResource;

public class WorkDayResourceFromEntityAssembler {
    public static WorkDayResource toResourceFromEntity(WorkDay entity) {
        return new WorkDayResource(
                entity.getId(),
                entity.getDay().toString(),
                entity.getStartAt(),
                entity.getEndAt()
        );
    }
}
