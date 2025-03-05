package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms;

import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillCertification;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.SkillCertificationResource;

public class SkillCertificationResourceFromEntityAssembler {
    public static SkillCertificationResource toResourceFromEntity(SkillCertification entity) {
        return new SkillCertificationResource(
                entity.getId(),
                entity.getCertificationName(),
                entity.getIssuer(),
                entity.getIssueDate(),
                entity.getExpirationDate(),
                entity.getFileId().fileId()
        );
    }
}
