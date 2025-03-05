package com.workshopngine.platform.staffmanagement.staff.infrastructure.persistence.jpa.repositories;

import com.workshopngine.platform.staffmanagement.staff.domain.model.aggregates.Mechanic;
import com.workshopngine.platform.staffmanagement.staff.domain.model.valueobjects.WorkshopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, String> {
    Collection<Mechanic> findAllByWorkshopId(WorkshopId workshopId);
}
