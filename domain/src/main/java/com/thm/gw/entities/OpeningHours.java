package com.thm.gw.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "opening_hours")
public class OpeningHours extends BaseEntity<Long> {

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;

    @Column(name = "opening_time", nullable = false)
    private String openingTime;

    @Column(name = "closing_time", nullable = false)
    private String closingTime;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
