package com.thm.gw.repositories;

import com.thm.gw.entities.PostalCode;
import com.thm.gw.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostalCodeRepository extends JpaRepository<PostalCode, Long> {

    @Query("SELECT p FROM PostalCode p WHERE p.code1 = :code1 AND p.city = :city")
    PostalCode findByCode1AndCity(@Param("code1") String code1, @Param("city") City city);
}
