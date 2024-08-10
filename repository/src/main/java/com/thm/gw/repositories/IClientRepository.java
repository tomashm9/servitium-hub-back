package com.thm.gw.repositories;

import com.thm.gw.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IClientRepository  extends JpaRepository<Client, Long> {
}
