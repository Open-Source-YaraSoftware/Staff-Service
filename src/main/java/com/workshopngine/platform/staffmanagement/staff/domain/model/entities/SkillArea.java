package com.workshopngine.platform.staffmanagement.staff.domain.model.entities;

import com.workshopngine.platform.staffmanagement.shared.domain.model.entities.AuditableModel;
import com.workshopngine.platform.staffmanagement.staff.domain.model.aggregates.Mechanic;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.ESkillLevel;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
