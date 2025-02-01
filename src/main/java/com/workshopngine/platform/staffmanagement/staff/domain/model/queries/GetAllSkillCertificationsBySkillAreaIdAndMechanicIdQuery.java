package com.workshopngine.platform.staffmanagement.staff.domain.model.queries;

public record GetAllSkillCertificationsBySkillAreaIdAndMechanicIdQuery(
        Long mechanicId,
        Long skillAreaId
) {
    public GetAllSkillCertificationsBySkillAreaIdAndMechanicIdQuery {
        if (mechanicId == null || mechanicId <= 0) {
            throw new IllegalArgumentException("mechanicId cannot be null or less than or equal to 0");
        }
        if (skillAreaId == null || skillAreaId <= 0) {
            throw new IllegalArgumentException("skillAreaId cannot be null or less than or equal to 0");
        }
    }
}
