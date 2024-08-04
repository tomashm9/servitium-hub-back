package com.thm.gw.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "manager")
@EqualsAndHashCode(callSuper = true)
public class Manager extends User {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
