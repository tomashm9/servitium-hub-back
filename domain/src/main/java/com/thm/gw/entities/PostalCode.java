package com.thm.gw.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "postal_code")
public class PostalCode extends BaseEntity<Long> {

    @Column(name = "code_1", nullable = false)
    private String code1;

    @Column(name = "code_2")
    private String code2;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public PostalCode(String code1, City city) {
        this.code1 = code1;
        this.city = city;
    }
}
