package com.thm.gw.mappers;

import com.thm.gw.dtos.reservation.ReservationDTO;
import com.thm.gw.entities.Client;
import com.thm.gw.entities.Reservation;
import com.thm.gw.entities.Service;
import com.thm.gw.enums.ReservationStatus;
import com.thm.gw.forms.reservation.ReservationUpdateForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface IReservationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "client", target = "client")
    @Mapping(source = "service", target = "service")
    @Mapping(source = "status", target = "status")
    Reservation toEntity(
            LocalDateTime date,
            Client client,
            Service service,
            ReservationStatus status
    );

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "service.id", target = "serviceId")
    ReservationDTO fromEntity(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromForm(ReservationUpdateForm form, @MappingTarget Reservation reservation);
}

