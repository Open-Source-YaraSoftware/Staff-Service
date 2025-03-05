package com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects;

import com.workshopngine.platform.staffmanagement.staff.domain.model.aggregates.Mechanic;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateWorkDayCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.WorkDay;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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

    public WorkDay addWorkDay(Mechanic mechanic, CreateWorkDayCommand command) {
        var workDay = new WorkDay(mechanic, command);
        this.workDays.add(workDay);
        return workDay;
    }

    public Boolean isAvailableAtRequestedTime(LocalDateTime requestedTime) {
        WorkDay requestedWorkDay = this.getWorkDays().stream().filter(day -> day.isWithinEstablishedRangeOfDays(requestedTime)).findFirst().orElse(null);
        if (requestedWorkDay == null) return false;
        return requestedWorkDay.isWithinEstablishedTimeRange(requestedTime);
    }
}
