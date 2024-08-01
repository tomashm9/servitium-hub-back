package com.thm.gw.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "manager")
public class Manager extends User {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    // Qualquer campo ou método específico para Manager pode ser adicionado aqui
}
