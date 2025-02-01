package com.workshopngine.platform.staffmanagement.staff.domain.services;

import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateMechanicCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateSkillAreaCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateSkillCertificationCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateWorkDayCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillArea;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillCertification;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.WorkDay;

import java.util.Optional;

public interface MechanicCommandService {
    Long handle(CreateMechanicCommand command);
    Optional<WorkDay> handle(CreateWorkDayCommand command);
    Optional<SkillArea> handle(CreateSkillAreaCommand command);
    Optional<SkillCertification> handle(CreateSkillCertificationCommand command);
}
