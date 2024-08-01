package com.thm.gw.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "service")
public class Service extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "duration", nullable = false)
    private int duration;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ServiceType type;

    @ManyToOne
    @JoinColumn(name = "subtype_id", nullable = false)
    private ServiceSubtype subtype;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
