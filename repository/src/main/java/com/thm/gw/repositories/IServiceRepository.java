package com.thm.gw.repositories;

import com.thm.gw.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IServiceRepository extends JpaRepository<Service, Long> {

    @Query("SELECT s FROM Service s WHERE s.company.id = :id")
    List<Service> findByCompanyId(Long id);
}
