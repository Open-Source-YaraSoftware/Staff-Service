package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms;

import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillArea;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.SkillAreaResource;

public class SkillAreaResourceFromEntityAssembler {
    public static SkillAreaResource toResourceFromEntity(SkillArea entity){
        return new SkillAreaResource(
                entity.getId(),
                entity.getArea(),
                entity.getLevel().toString()
        );
    }
}
