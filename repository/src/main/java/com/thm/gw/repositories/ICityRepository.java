package com.thm.gw.repositories;

import com.thm.gw.entities.City;
import com.thm.gw.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c FROM City c WHERE c.name = :name")
    Optional<City> findByName(@Param("name") String name);

    @Query("SELECT c FROM City c WHERE c.name = :name AND c.country = :country")
    City findByNameAndCountry(@Param("name") String name, @Param("country") Country country);
}
