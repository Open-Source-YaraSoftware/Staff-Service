package com.workshopngine.platform.staffmanagement.staff.domain.model.queries;

public record GetAllSkillCertificationsBySkillAreaIdAndMechanicIdQuery(
        String mechanicId,
        String skillAreaId
) {
    public GetAllSkillCertificationsBySkillAreaIdAndMechanicIdQuery {
        if (mechanicId == null || mechanicId.isBlank()) {
            throw new IllegalArgumentException("mechanicId cannot be null or blank");
        }
        if (skillAreaId == null || skillAreaId.isBlank()) {
            throw new IllegalArgumentException("skillAreaId cannot be null or blank");
        }
    }
}
