package com.thm.gw.services.impls;

import com.thm.gw.dtos.reservation.ReservationDTO;
import com.thm.gw.entities.Client;
import com.thm.gw.entities.Owner;
import com.thm.gw.entities.Reservation;
import com.thm.gw.enums.ReservationStatus;
import com.thm.gw.exceptions.NotAllowedException;
import com.thm.gw.exceptions.Service.ServiceNotFoundException;
import com.thm.gw.exceptions.reservation.ReservationNotFoundException;
import com.thm.gw.exceptions.reservation.ReservationStatusAlreadyDefinedException;
import com.thm.gw.forms.reservation.ReservationForm;
import com.thm.gw.forms.reservation.ReservationStatusForm;
import com.thm.gw.forms.reservation.ReservationUpdateForm;
import com.thm.gw.mappers.IReservationMapper;
import com.thm.gw.repositories.IReservationRepository;
import com.thm.gw.repositories.IServiceRepository;
import com.thm.gw.services.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final IServiceRepository serviceRepository;
    private final IReservationMapper reservationMapper;
    private final AuthServiceImpl authService;

    @Override
    public List<ReservationDTO> getAllByServiceId(Long id) {
        com.thm.gw.entities.Service service = serviceRepository.findById(id)
                .orElseThrow(ServiceNotFoundException::new);

        List<Reservation> reservations = reservationRepository.findAllByServiceId(service.getId());

        return reservations.stream()
                .map(reservationMapper::fromEntity)
                .toList();
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(ReservationNotFoundException::new);

        return reservationMapper.fromEntity(reservation);
    }

    @Override
    public ReservationDTO getReservationByIdAndClientId(Long id) {
        Owner currentUser = authService.getAuthenticatedOwner();

        Reservation reservation = reservationRepository.findByIdAndClientId(id, currentUser.getId())
                .orElseThrow(ReservationNotFoundException::new);

        return reservationMapper.fromEntity(reservation);
    }

    @Override
    @Transactional
    public ReservationDTO addReservation(ReservationForm form) {
        Client client = authService.getAuthenticatedClient();

        Optional<Reservation> existingReservation = reservationRepository.findByClientIdAndServiceId(client.getId(), form.serviceId());

        if (existingReservation.isPresent()) {
            throw new NotAllowedException("You have already made a reservation for this service.");
        }

        com.thm.gw.entities.Service service = serviceRepository.findById(form.serviceId())
                .orElseThrow(ServiceNotFoundException::new);

        LocalDateTime reservationDate = LocalDateTime.now();

        Reservation reservation = reservationMapper.toEntity(reservationDate, client, service, ReservationStatus.PENDING);

        return reservationMapper.fromEntity(reservationRepository.save(reservation));
    }

    @Override
    @Transactional
    public ReservationDTO updateReservation(Long id, ReservationUpdateForm form) {
        Client client = authService.getAuthenticatedClient();

        Reservation reservation = reservationRepository.findByIdAndClientId(id, client.getId())
                .orElseThrow(ReservationNotFoundException::new);

        reservationMapper.updateEntityFromForm(form, reservation);

        return reservationMapper.fromEntity(reservationRepository.save(reservation));
    }

    @Override
    @Transactional
    public ReservationDTO deleteReservation(Long id) {
        Client client = authService.getAuthenticatedClient();

        Reservation reservation = reservationRepository.findByIdAndClientId(id, client.getId())
                .orElseThrow(ReservationNotFoundException::new);

        reservationRepository.deleteById(id);

        return reservationMapper.fromEntity(reservation);
    }

    @Override
    @Transactional
    public ReservationDTO cancelReservation(Long id) {
        Client client = authService.getAuthenticatedClient();

        Reservation reservation = reservationRepository.findByIdAndClientId(id, client.getId())
                .orElseThrow(ReservationNotFoundException::new);

        if (reservation.getStatus().equals(ReservationStatus.CANCELLED)) {
            throw new NotAllowedException("Reservation is already cancelled.");
        }

        reservation.setStatus(ReservationStatus.CANCELLED);


        return reservationMapper.fromEntity(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDTO triggerReservationStatus(Long id, ReservationStatusForm form) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(ReservationNotFoundException::new);

        ReservationStatus status= form.status();

        if (reservation.getStatus().equals(status)) {
            throw new ReservationStatusAlreadyDefinedException(status);
        }

        reservation.setStatus(status);

        return reservationMapper.fromEntity(reservationRepository.save(reservation));
    }
}
