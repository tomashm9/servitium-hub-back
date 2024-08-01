package com.thm.gw.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "owner")
public class Owner extends User {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    // Qualquer campo ou método específico para Owner pode ser adicionado aqui
}
