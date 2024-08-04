package com.thm.gw.repositories;

import com.thm.gw.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOwnerRepository extends JpaRepository<Owner, Long> {

    @Modifying
    @Query("UPDATE Owner o SET o.isActive = :isActive WHERE o.id IN :ids")
    void updateAllActiveByIds(List<Long> ids, boolean isActive);

    @Query("SELECT o.id FROM Owner o WHERE o.company.id = :companyId")
    List<Long> findAllOwnersIdsByCompany(Long companyId);
}