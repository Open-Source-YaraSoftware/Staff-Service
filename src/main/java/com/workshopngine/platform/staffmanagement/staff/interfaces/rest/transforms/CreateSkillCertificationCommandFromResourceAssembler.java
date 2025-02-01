package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms;

import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateSkillCertificationCommand;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateSkillCertificationResource;
import org.springframework.web.multipart.MultipartFile;

public class CreateSkillCertificationCommandFromResourceAssembler {
    public static CreateSkillCertificationCommand toCommandFromResource(Long mechanicId, Long skillAreaId, MultipartFile file, CreateSkillCertificationResource resource) {
        return new CreateSkillCertificationCommand(
            mechanicId,
            skillAreaId,
            resource.certificationName(),
            resource.issuer(),
            resource.issueDate(),
            resource.expirationDate(),
            file
        );
    }
}
