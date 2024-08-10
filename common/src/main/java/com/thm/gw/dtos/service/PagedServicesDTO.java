package com.thm.gw.dtos.service;

import java.util.List;

public record PagedServicesDTO(
        List<ServiceDTO> services,
        Integer elementsPerPage,
        Long totalElements,
        Integer totalPages
) {
}
