package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms;

import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateWorkDayCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.EDay;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateWorkDayResource;

public class CreateWorkDayCommandFromResourceAssembler {
    public static CreateWorkDayCommand toCommandFromResource(String mechanicId, CreateWorkDayResource resource) {
        return new CreateWorkDayCommand(
            mechanicId,
            EDay.fromString(resource.day()),
            resource.startAt(),
            resource.endAt()
        );
    }
}
