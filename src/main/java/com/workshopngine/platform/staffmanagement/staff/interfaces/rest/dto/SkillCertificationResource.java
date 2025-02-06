package com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto;

import java.time.LocalDateTime;

public record SkillCertificationResource(
        String id,
        String certificationName,
        String issuer,
        LocalDateTime issueDate,
        LocalDateTime expirationDate,
        String fileId
) {
}
