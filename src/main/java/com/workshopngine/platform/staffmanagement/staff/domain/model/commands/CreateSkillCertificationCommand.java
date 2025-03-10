package com.workshopngine.platform.staffmanagement.staff.domain.model.commands;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public record CreateSkillCertificationCommand(
        String mechanicId,
        String skillAreaId,
        String certificationName,
        String issuer,
        LocalDateTime issueDate,
        LocalDateTime expirationDate,
        MultipartFile file
) {
}
