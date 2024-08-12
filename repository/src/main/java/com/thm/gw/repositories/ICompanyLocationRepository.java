package com.thm.gw.repositories;

import com.thm.gw.entities.CompanyLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICompanyLocationRepository extends JpaRepository<CompanyLocation, Long> {

    List<CompanyLocation> findAllByCompanyId(Long companyId);

    @Query("SELECT cl FROM CompanyLocation cl WHERE cl.company.id = :companyId")
    Optional<CompanyLocation> findByCompanyId(@Param("companyId") Long companyId);
}
