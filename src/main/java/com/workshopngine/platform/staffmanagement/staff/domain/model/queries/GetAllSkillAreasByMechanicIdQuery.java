package com.workshopngine.platform.staffmanagement.staff.domain.model.queries;

public record GetAllSkillAreasByMechanicIdQuery(Long mechanicId) {
    public GetAllSkillAreasByMechanicIdQuery {
        if (mechanicId == null || mechanicId <= 0) {
            throw new IllegalArgumentException("Invalid mechanicId");
        }
    }
}
