package com.example.hotelBooking.reservation;

import com.example.hotelBooking.customer.Customer;
import com.example.hotelBooking.customer.CustomerService;
import com.example.hotelBooking.room.Room;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ReservationService {

    @Resource
    ReservationRepository reservationRepository;
    @Resource
    CustomerService customerService;

    public void saveReservation(ReservationRequest reservationRequest) {
        Customer customer = customerService.saveCustomer(reservationRequest);
        Reservation reservation = makeReservationObject(reservationRequest);
        reservation.setCustomerId(customer.getId());
        List<Room> freeNumbersList = reservationRepository.getFreeNumbersList(reservationRequest.getCheckIn(),
                reservationRequest.getCheckOut(), reservationRequest.getRoomType());
        Random random = new Random();
        reservation.setRoom(freeNumbersList.get(random.nextInt(freeNumbersList.size())).getNumber());
        reservationRepository.saveReservation(reservation);
    }

    public Reservation makeReservationObject(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setId(UUID.randomUUID());
        reservation.setOpen(reservationRequest.getCheckIn());
        reservation.setClose(reservationRequest.getCheckOut());
        return reservation;
    }

    public List<ReservationResponse> getReservations() {
        return reservationRepository.getReservations(LocalDate.now());
    }
}
