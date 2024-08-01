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
@Table(name = "company_location")
public class CompanyLocation extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "postal_code_id", nullable = false)
    private PostalCode postalCode;
}
