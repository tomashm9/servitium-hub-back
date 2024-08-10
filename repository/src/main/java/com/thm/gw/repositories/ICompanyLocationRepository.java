package com.thm.gw.repositories;

import com.thm.gw.entities.CompanyLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompanyLocationRepository extends JpaRepository<CompanyLocation, Long> {
    
    List<CompanyLocation> findAllByCompanyId(Long companyId);
}
