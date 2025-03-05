package com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects;

public enum ESkillLevel {
    JUNIOR,
    SENIOR,
    EXPERT;

    public static ESkillLevel fromString(String level) {
        return switch (level) {
            case "JUNIOR" -> JUNIOR;
            case "SENIOR" -> SENIOR;
            case "EXPERT" -> EXPERT;
            default -> throw new IllegalArgumentException("Invalid skill level: " + level);
        };
    }
}
