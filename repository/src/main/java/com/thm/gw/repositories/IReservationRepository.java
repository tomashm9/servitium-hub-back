package com.thm.gw.repositories;

import com.thm.gw.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.service.id = :serviceId")
    List<Reservation> findAllByServiceId(Long serviceId);

    @Query("SELECT r FROM Reservation r WHERE r.id = :id AND r.client.id = :clientId")
    Optional<Reservation> findByIdAndClientId(Long id, Long clientId);

    @Query("SELECT r FROM Reservation r WHERE r.client.id = :clientId AND r.service.id = :serviceId")
    Optional<Reservation> findByClientIdAndServiceId(Long clientId, Long serviceId);
}
