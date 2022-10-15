package com.example.hotelBooking.customer;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Configuration
public class Customer {
    UUID id;
    String firstName;
    String lastName;
    String personalCode;
    String email;
}
