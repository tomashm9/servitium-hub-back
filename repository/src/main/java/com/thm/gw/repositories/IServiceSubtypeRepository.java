package com.thm.gw.repositories;

import com.thm.gw.entities.ServiceSubtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServiceSubtypeRepository extends JpaRepository<ServiceSubtype, Long> {

    @Query("SELECT s FROM ServiceSubtype s WHERE s.type.id = :typeId")
    List<ServiceSubtype> findByTypeId(Long typeId);
}
