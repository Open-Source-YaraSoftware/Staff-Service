package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms;

import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateSkillAreaCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.ESkillLevel;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateSkillAreaResource;

public class CreateSkillAreaCommandFromResourceAssembler {
    public static CreateSkillAreaCommand toCommandFromResource(Long mechanicId, CreateSkillAreaResource resource) {
        return new CreateSkillAreaCommand(
                mechanicId,
                resource.area(),
                ESkillLevel.fromString(resource.level())
        );
    }
}
