package com.workshopngine.platform.staffmanagement.staff.domain.model.entities;

import com.workshopngine.platform.staffmanagement.shared.domain.model.entities.AuditableModel;
import com.workshopngine.platform.staffmanagement.staff.domain.model.aggregates.Mechanic;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateWorkDayCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.EDay;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
public class WorkDay extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "mechanic_id", nullable = false)
    private Mechanic mechanic;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EDay day;

    private LocalTime startAt;

    private LocalTime endAt;

    public WorkDay() {
        super();
        this.startAt = null;
        this.endAt = null;
    }

    public WorkDay(Mechanic mechanic, CreateWorkDayCommand command) {
        super();
        this.mechanic = mechanic;
        this.day = command.day();
        this.startAt = command.startAt();
        this.endAt = command.endAt();
    }
}
