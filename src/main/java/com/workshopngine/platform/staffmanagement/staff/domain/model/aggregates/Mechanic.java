package com.workshopngine.platform.staffmanagement.staff.domain.model.aggregates;

import com.workshopngine.platform.staffmanagement.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateMechanicCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateSkillAreaCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateSkillCertificationCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillArea;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillCertification;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
public class Mechanic extends AuditableAbstractAggregateRoot<Mechanic> {
    @Embedded
    private WorkshopId workshopId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "mechanic")
    private Collection<SkillArea> skillAreas;

    @Embedded
    private WorkSchedule workSchedule;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EAvailabilityStatus availabilityStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EOperationalStatus operationalStatus;

    public Mechanic() {
        super();
        this.skillAreas = new ArrayList<>();
        this.workSchedule = new WorkSchedule();
    }

    public Mechanic(CreateMechanicCommand command) {
        this();
        this.workshopId = command.workshopId();
        this.availabilityStatus = EAvailabilityStatus.AVAILABLE;
        this.operationalStatus = EOperationalStatus.IDLE;
    }

    public SkillArea addSkillArea(CreateSkillAreaCommand command) {
        var skillArea = new SkillArea(this, command);
        this.skillAreas.add(skillArea);
        return skillArea;
    }

    public SkillArea getSkillArea(String skillAreaId) {
        return skillAreas.stream()
                .filter(skillArea -> skillArea.getId().equals(skillAreaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Skill area not found"));
    }

    public SkillCertification addSkillCertification(CreateSkillCertificationCommand command, FileId fileId) {
        var skillArea = getSkillArea(command.skillAreaId());
        return skillArea.addSkillCertification(command, fileId);
    }

    public SkillArea getSkillAreaById(String skillAreaId) {
        return skillAreas.stream()
                .filter(skillArea -> skillArea.getId().equals(skillAreaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Skill area with ID %s not found".formatted(skillAreaId)));
    }
}
