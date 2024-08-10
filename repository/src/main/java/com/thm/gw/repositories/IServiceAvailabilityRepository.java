package com.thm.gw.repositories;

import com.thm.gw.entities.ServiceAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IServiceAvailabilityRepository extends JpaRepository<ServiceAvailability, Long> {

    @Query("SELECT sa FROM ServiceAvailability sa WHERE sa.service.id = :serviceId AND sa.date = :date")
    List<ServiceAvailability> findByServiceAndDate(Long serviceId, LocalDate date);

    @Query("SELECT sa FROM ServiceAvailability sa WHERE sa.isBooked = false AND sa.date >= :startDate AND sa.date <= :endDate")
    List<ServiceAvailability> findAvailableSlots(LocalDate startDate, LocalDate endDate);
}
