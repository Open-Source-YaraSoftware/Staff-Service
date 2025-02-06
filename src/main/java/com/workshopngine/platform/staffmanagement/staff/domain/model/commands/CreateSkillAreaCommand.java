package com.workshopngine.platform.staffmanagement.staff.domain.model.commands;

import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.ESkillLevel;

public record CreateSkillAreaCommand(
        String mechanicId,
        String area,
        ESkillLevel level
) {
}
