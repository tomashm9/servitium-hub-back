package com.thm.gw.specifications;

import com.thm.gw.entities.Company;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Map;

public class CompanySpecifications {

    public static Specification<Company> withName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Company> withDescription(String description) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }

    public static Specification<Company> withWebsiteUrl(String websiteUrl) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("websiteUrl"), "%" + websiteUrl + "%");
    }

    public static Specification<Company> withEstablishmentDate(LocalDate establishmentDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("establishmentDate"), establishmentDate);
    }

    public static Specification<Company> filterByFilters(Map<String, String> filters) {
        Specification<Company> spec = Specification.where(null);

        if (filters.containsKey("name")) {
            spec = spec.and(withName(filters.get("name")));
        }

        if (filters.containsKey("description")) {
            spec = spec.and(withDescription(filters.get("description")));
        }

        if (filters.containsKey("websiteUrl")) {
            spec = spec.and(withWebsiteUrl(filters.get("websiteUrl")));
        }

        if (filters.containsKey("establishmentDate")) {
            LocalDate establishmentDate = LocalDate.parse(filters.get("establishmentDate"));
            spec = spec.and(withEstablishmentDate(establishmentDate));
        }

        return spec;
    }
}
