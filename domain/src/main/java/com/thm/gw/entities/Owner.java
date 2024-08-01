package com.thm.gw.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "owner")
@EqualsAndHashCode(callSuper = true)
public class Owner extends User {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
