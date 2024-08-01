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
@Table(name = "postal_code")
public class PostalCode extends BaseEntity<Long> {

    @Column(name = "code_1", nullable = false)
    private String code1;

    @Column(name = "code_2", nullable = false)
    private String code2;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
