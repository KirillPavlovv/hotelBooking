package com.example.hotelBooking.reservation;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Configuration
public class ReservationRequest {
    LocalDate checkIn;
    LocalDate checkOut;
    String email;
    String firstName;
    String lastName;
    String personalCode;
    int roomType;
}
