package com.workshopngine.platform.staffmanagement.staff.application.internal.queryservices;

import com.workshopngine.platform.staffmanagement.staff.domain.model.aggregates.Mechanic;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillArea;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillCertification;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.WorkDay;
import com.workshopngine.platform.staffmanagement.staff.domain.model.queries.*;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.WorkshopId;
import com.workshopngine.platform.staffmanagement.staff.domain.services.MechanicQueryService;
import com.workshopngine.platform.staffmanagement.staff.infrastructure.persistence.jpa.repositories.MechanicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MechanicQueryServiceImpl implements MechanicQueryService {
    private final MechanicRepository mechanicRepository;

    @Override
    public Optional<Mechanic> handle(GetMechanicByIdQuery query) {
        return mechanicRepository.findById(query.mechanicId());
    }

    @Override
    public Collection<Mechanic> handle(GetAllMechanicsByWorkshopIdQuery query) {
        var workshopId = new WorkshopId(query.workshopId());
        return mechanicRepository.findAllByWorkshopId(workshopId);
    }

    @Override
    public Collection<WorkDay> handle(GetAllWorkDaysByMechanicIdQuery query) {
        var mechanic = mechanicRepository.findById(query.mechanicId());
        if (mechanic.isEmpty()) throw new IllegalArgumentException("Mechanic with ID %s not found".formatted(query.mechanicId()));
        return mechanic.get().getWorkSchedule().getWorkDays();
    }

    @Override
    public Collection<SkillArea> handle(GetAllSkillAreasByMechanicIdQuery query) {
        var mechanic = mechanicRepository.findById(query.mechanicId());
        if (mechanic.isEmpty()) throw new IllegalArgumentException("Mechanic with ID %s not found".formatted(query.mechanicId()));
        return mechanic.get().getSkillAreas();
    }

    @Override
    public Collection<SkillCertification> handle(GetAllSkillCertificationsBySkillAreaIdAndMechanicIdQuery query) {
        var mechanic = mechanicRepository.findById(query.mechanicId());
        if (mechanic.isEmpty()) throw new IllegalArgumentException("Mechanic with ID %s not found".formatted(query.mechanicId()));
        var skillArea = mechanic.get().getSkillAreaById(query.skillAreaId());
        return skillArea.getCertifications();
    }
}
