package com.workshopngine.platform.staffmanagement.staff.domain.model.entities;

import com.workshopngine.platform.staffmanagement.shared.domain.model.entities.AuditableModel;
import com.workshopngine.platform.staffmanagement.staff.domain.model.commands.CreateSkillCertificationCommand;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.FileId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class SkillCertification extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "skill_area_id", nullable = false)
    private SkillArea skillArea;

    @NotBlank
    private String certificationName;

    @NotBlank
    private String issuer;

    @NotBlank
    private LocalDateTime issueDate;

    @NotBlank
    private LocalDateTime expirationDate;

    @Embedded
    private FileId fileId;

    public SkillCertification() {
        super();
    }

    public SkillCertification(SkillArea skillArea, CreateSkillCertificationCommand command, FileId fileId) {
        super();
        this.fileId = fileId;
        this.skillArea = skillArea;
        this.certificationName = command.certificationName();
        this.issuer = command.issuer();
        this.issueDate = command.issueDate();
        this.expirationDate = command.expirationDate();
    }
}
