package com.thm.gw.dtos.company;

import java.util.List;

public record PagedCompaniesDTO(
        List<CompanyDTO> companies,
        Integer elementsPerPage,
        Long totalElements,
        Integer totalPages
) { }
