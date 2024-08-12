package com.thm.gw.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "company_location")
public class CompanyLocation extends BaseEntity<Long> {

    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

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

    @OneToMany(mappedBy = "companyLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpeningHours> openingHours;

    public CompanyLocation(String addressLine1, String addressLine2, Company company, Country country, City city, PostalCode postalCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.company = company;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
    }
}
