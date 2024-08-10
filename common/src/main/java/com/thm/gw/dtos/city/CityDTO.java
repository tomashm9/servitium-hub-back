package com.thm.gw.dtos.city;

import com.thm.gw.entities.Country;

public record CityDTO(
        Long id,
        String name,
        Country country
) {}
