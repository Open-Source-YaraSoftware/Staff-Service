package com.workshopngine.platform.staffmanagement.staff.application.internal.commandservices;

import com.workshopngine.platform.staffmanagement.staff.application.internal.outboundservices.acl.FileManagementContextFacade;
import com.workshopngine.platform.staffmanagement.staff.domain.model.aggregates.Mechanic;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateMechanicCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateSkillAreaCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateSkillCertificationCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateWorkDayCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillArea;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.SkillCertification;
import com.workshopngine.platform.staffmanagement.staff.domain.model.entities.WorkDay;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.FileId;
import com.workshopngine.platform.staffmanagement.staff.domain.services.MechanicCommandService;
import com.workshopngine.platform.staffmanagement.staff.infrastructure.persistence.jpa.repositories.MechanicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MechanicCommandServiceImpl implements MechanicCommandService {
    private final MechanicRepository mechanicRepository;
    private final FileManagementContextFacade fileManagementContextFacade;

    @Override
    public Long handle(CreateMechanicCommand command) {
        var mechanic = new Mechanic(command);
        try {
            mechanicRepository.save(mechanic);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving mechanic: " + e.getMessage());
        }
        return mechanic.getId();
    }

    @Override
    public Optional<WorkDay> handle(CreateWorkDayCommand command) {
        var mechanic = mechanicRepository.findById(command.mechanicId());
        if (mechanic.isEmpty()) throw new IllegalArgumentException("Mechanic with ID %s not found".formatted(command.mechanicId()));
        var workDay = mechanic.get().getWorkSchedule().addWorkDay(mechanic.get(), command);
        try {
            mechanicRepository.save(mechanic.get());
            return Optional.of(workDay);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving work day: " + e.getMessage());
        }
    }

    @Override
    public Optional<SkillArea> handle(CreateSkillAreaCommand command) {
        var mechanic = mechanicRepository.findById(command.mechanicId());
        if (mechanic.isEmpty()) throw new IllegalArgumentException("Mechanic with ID %s not found".formatted(command.mechanicId()));
        var skillArea = mechanic.get().addSkillArea(command);
        try {
            mechanicRepository.save(mechanic.get());
            return Optional.of(skillArea);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving skill area: " + e.getMessage());
        }
    }

    @Override
    public Optional<SkillCertification> handle(CreateSkillCertificationCommand command) {
        var mechanic = mechanicRepository.findById(command.mechanicId());
        if (mechanic.isEmpty()) throw new IllegalArgumentException("Mechanic with ID %s not found".formatted(command.mechanicId()));
        var fileId = fileManagementContextFacade.fetchUploadFile(command.file());
        if (fileId.id() == null) throw new IllegalArgumentException("Error while uploading file");
        var skillCertification = mechanic.get().addSkillCertification(command, new FileId(fileId.id()));
        try {
            mechanicRepository.save(mechanic.get());
            return Optional.of(skillCertification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving skill certification: " + e.getMessage());
        }
    }
}
