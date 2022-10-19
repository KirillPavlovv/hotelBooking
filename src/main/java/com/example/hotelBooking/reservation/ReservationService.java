package com.example.hotelBooking.reservation;

import com.example.hotelBooking.customer.Customer;
import com.example.hotelBooking.customer.CustomerRepository;
import com.example.hotelBooking.customer.CustomerService;
import com.example.hotelBooking.room.Room;
import com.example.hotelBooking.validation.ValidationService;
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
    @Resource
    ValidationService validationService;
    @Resource
    CustomerRepository customerRepository;

    public void saveReservation(ReservationRequest reservationRequest) {
        validationService.isStartDateBeforeCurrentDate(reservationRequest.getCheckIn());
        validationService.isStartDateIsBeforeEndDate(reservationRequest.getCheckIn(), reservationRequest.getCheckOut());
        Boolean isCustomerExist = customerRepository.customerExists(reservationRequest.personalCode);
        Reservation reservation = makeReservationObject(reservationRequest);
        setRandomRoomNumber(reservationRequest.getCheckIn(), reservationRequest.getCheckOut(),
                reservationRequest.getRoomType(), reservation);
        Customer customer;
        if (isCustomerExist){
            customer = customerService.updateCustomer(reservationRequest);

        }
        else{
            customer = customerService.saveCustomer(reservationRequest);
        }
        reservation.setCustomerId(customer.getId());
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

    public void updateReservation(ReservationResponse reservationResponse) {
        validationService.isStartDateIsBeforeEndDate(reservationResponse.getCheckIn(), reservationResponse.getCheckOut());
        Reservation reservation = new Reservation();
        reservation.setOpen(reservationResponse.getCheckIn());
        reservation.setClose(reservationResponse.getCheckOut());
        reservation.setId(reservationResponse.getId());
        setRandomRoomNumber(reservationResponse.getCheckIn(), reservationResponse.getCheckOut(),
                reservationResponse.getRoomType(), reservation);
        reservationRepository.updateReservation(reservation);

    }

    private void setRandomRoomNumber(LocalDate checkIn, LocalDate checkOut, int roomType, Reservation reservation) {
        List<Room> freeNumbersList = reservationRepository.getFreeNumbersList(checkIn, checkOut,
                roomType);
        Random random = new Random();
        reservation.setRoom(freeNumbersList.get(random.nextInt(freeNumbersList.size())).getNumber());
    }

    public void deleteReservation(String personalCode) {
        Customer customer = customerRepository.findByPersonalCode(personalCode);
        LocalDate date = LocalDate.now().plusDays(3);
        reservationRepository.deleteByCustomerId(customer.getId(), date);
    }
}
