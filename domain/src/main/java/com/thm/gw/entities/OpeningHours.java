package com.thm.gw.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    @JoinColumn(name = "company_location_id", nullable = false)
    private CompanyLocation companyLocation;

    public OpeningHours(String dayOfWeek, String openingTime, String closingTime) {
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
}
