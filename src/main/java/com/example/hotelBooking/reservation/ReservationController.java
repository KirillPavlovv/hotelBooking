package com.example.hotelBooking.reservation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ReservationController {

    @Resource
    ReservationService reservationService;

    @PostMapping("/reservation")
    public void saveReservationRequest(@RequestBody ReservationRequest reservationRequest) {
        reservationService.saveReservationRequest(reservationRequest);
    }
    @GetMapping("/reservationsList")
    public List<ReservationResponse> getReservations() {
       return reservationService.getReservations();
    }
}
