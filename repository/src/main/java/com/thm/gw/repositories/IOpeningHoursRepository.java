package com.thm.gw.repositories;

import com.thm.gw.entities.OpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOpeningHoursRepository extends JpaRepository<OpeningHours, Long> {

    @Query("SELECT o FROM OpeningHours o WHERE o.companyLocation.id = :companyLocationId")
    List<OpeningHours> findByCompanyLocationId(@Param("companyLocationId") Long companyLocationId);
}
