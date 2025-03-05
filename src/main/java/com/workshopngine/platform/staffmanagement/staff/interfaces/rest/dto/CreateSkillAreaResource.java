package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto;

import lombok.Builder;

@Builder
public record CreateSkillAreaResource(
        String area,
        String level
) {
}
