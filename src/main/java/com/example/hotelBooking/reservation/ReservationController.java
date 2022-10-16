package com.example.hotelBooking.reservation;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
public class ReservationController {

    @Resource
    ReservationService reservationService;
    @Resource
    ReservationRepository reservationRepository;
    @PostMapping("/reservation")
    public void saveReservationRequest(@RequestBody ReservationRequest reservationRequest) {
        reservationService.saveReservationRequest(reservationRequest);
    }
    @GetMapping("/reservationsList")
    public List<ReservationResponse> getReservations() {
        return reservationService.getReservations();
    }
    @DeleteMapping("/deleteReservation")
    public void deleteReservation(@RequestParam UUID id) {
        reservationRepository.deleteReservation(id);
    }
}
