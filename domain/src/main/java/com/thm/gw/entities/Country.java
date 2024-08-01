package com.thm.gw.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "country")
public class Country extends BaseEntity<Long> {

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
