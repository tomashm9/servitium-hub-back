package com.thm.gw.repositories;

import com.thm.gw.entities.Service;
import com.thm.gw.entities.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServiceTypeRepository extends JpaRepository<ServiceType, Long> {

    @Query("SELECT s FROM Service s JOIN s.company c JOIN c.companyLocations l WHERE l.country.name = :country AND l.city.name = :city AND l.postalCode.code1 = :postalCode")
    List<Service> findByLocation(String country, String city, String postalCode);
}
