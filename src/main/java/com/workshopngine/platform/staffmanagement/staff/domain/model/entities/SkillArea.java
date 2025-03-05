package com.workshopngine.platform.staffmanagement.staff.domain.model.entities;

import com.workshopngine.platform.staffmanagement.shared.domain.model.entities.AuditableModel;
import com.workshopngine.platform.staffmanagement.staff.domain.model.aggregates.Mechanic;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateSkillAreaCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateSkillCertificationCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.ESkillLevel;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.FileId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
public class SkillArea extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "mechanic_id", nullable = false)
    private Mechanic mechanic;

    @NotBlank
    private String area;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESkillLevel level;

    @OneToMany(mappedBy = "skillArea", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<SkillCertification> certifications;

    public SkillArea() {
        super();
        this.certifications = new ArrayList<>();
    }

    public SkillArea(Mechanic mechanic, CreateSkillAreaCommand command) {
        this();
        this.mechanic = mechanic;
        this.area = command.area();
        this.level = command.level();
    }

    public SkillCertification addSkillCertification(CreateSkillCertificationCommand command, FileId fileId) {
        SkillCertification certification = new SkillCertification(this, command, fileId);
        this.certifications.add(certification);
        return certification;
    }
}
