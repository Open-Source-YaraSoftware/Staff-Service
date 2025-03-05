package com.workshopngine.platform.staffmanagement.staff.domain.model.queries;

public record GetAllSkillAreasByMechanicIdQuery(String mechanicId) {
    public GetAllSkillAreasByMechanicIdQuery {
        if (mechanicId == null || mechanicId.isEmpty()) {
            throw new IllegalArgumentException("Mechanic id cannot be null or empty");
        }
    }
}
