package com.example.hotelBooking.reservation;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ReservationService {

    @Resource
    ReservationRepository reservationRepository;

    public HashMap<String, Integer> getReservationsByDate(LocalDate open, LocalDate close) {
        reservationRepository.getBookedNumbers(open, close);
        return null;
    }

    public void saveReservationRequest(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setId(UUID.randomUUID());
        reservation.setOpen(reservationRequest.getCheckIn());
        reservation.setClose(reservationRequest.getCheckOut());
    }
}
