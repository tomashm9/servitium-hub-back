package com.thm.gw.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "owner")
@EqualsAndHashCode(callSuper = true)
public class Owner extends User {

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Owner() {
        this.isActive = true;
    }

    public Owner(Company company) {
        this();
        this.company = company;
    }
}
