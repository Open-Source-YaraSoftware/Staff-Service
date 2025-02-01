package com.workshopngine.platform.staffmanagement.staff.domain.model.aggregates;

import com.workshopngine.platform.staffmanagement.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillArea;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.EAvailabilityStatus;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.EOperationalStatus;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.WorkSchedule;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.WorkshopId;
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
}
