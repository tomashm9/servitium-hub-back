package com.thm.gw.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "company")
public class Company extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "establishment_date")
    private LocalDate establishmentDate;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone_number")
    private String contactPhoneNumber;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OpeningHours> openingHours = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Owner> owners = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CompanyLocation> companyLocations = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Service> services = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CompanyImage> images = new HashSet<>();

    public Company() {
        this.owners = new HashSet<>();
    }

    public Company(
            String name,
            String description,
            String websiteUrl,
            LocalDate establishmentDate,
            String contactName,
            String contactPhoneNumber,
            boolean isActive
    ) {
        this();
        this.name = name;
        this.description = description;
        this.websiteUrl = websiteUrl;
        this.establishmentDate = establishmentDate;
        this.contactName = contactName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.isActive = isActive;
    }

    public Company(String name) {
        this.name = name;
    }
}
