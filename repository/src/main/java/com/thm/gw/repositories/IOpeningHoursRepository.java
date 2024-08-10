package com.thm.gw.repositories;

import com.thm.gw.entities.OpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOpeningHoursRepository extends JpaRepository<OpeningHours, Long> {
    List<OpeningHours> findByCompanyId(Long companyId);
}
