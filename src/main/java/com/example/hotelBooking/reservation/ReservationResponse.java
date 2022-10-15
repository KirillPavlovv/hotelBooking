package com.example.hotelBooking.reservation;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Configuration
public class ReservationResponse {
    String customerFirstName;
    String customerLastName;
    int roomNumber;
    LocalDate checkIn;
    LocalDate checkOut;
}
