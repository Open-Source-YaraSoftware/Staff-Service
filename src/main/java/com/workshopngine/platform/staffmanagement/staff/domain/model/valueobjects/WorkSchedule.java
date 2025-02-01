package com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects;

import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.WorkDay;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Embeddable
public class WorkSchedule {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "mechanic")
    private Collection<WorkDay> workDays;

    public WorkSchedule() {
        this.workDays = new ArrayList<>();
    }
}
