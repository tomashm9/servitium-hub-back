package com.thm.gw.repositories;

import com.thm.gw.entities.CompanyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompanyImageRepository extends JpaRepository<CompanyImage, Long> {
    List<CompanyImage> findByCompanyId(Long companyId);
}
