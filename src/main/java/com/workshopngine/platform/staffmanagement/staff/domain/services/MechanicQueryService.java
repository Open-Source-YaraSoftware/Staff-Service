package com.workshopngine.platform.staffmanagement.staff.domain.services;

import com.workshopngine.platform.staffmanagement.staff.domain.model.aggregates.Mechanic;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillArea;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillCertification;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.WorkDay;
import com.workshopngine.platform.staffmanagement.staff.domain.model.queries.*;

import java.util.Collection;
import java.util.Optional;

public interface MechanicQueryService {
    Optional<Mechanic> handle(GetMechanicByIdQuery query);
    Collection<Mechanic> handle(GetAllMechanicsByWorkshopIdQuery query);
    Collection<WorkDay> handle(GetAllWorkDaysByMechanicIdQuery query);
    Collection<SkillArea> handle(GetAllSkillAreasByMechanicIdQuery query);
    Collection<SkillCertification> handle(GetAllSkillCertificationsBySkillAreaIdAndMechanicIdQuery query);
    Boolean handle(IsAvailableMechanicByIdAndRequestedTimeQuery query);
}
