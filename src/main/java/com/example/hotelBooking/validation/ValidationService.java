package com.example.hotelBooking.validation;


import com.example.hotelBooking.validation.infrastructure.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidationService {

    public static final String EMAIL_ALREADY_TAKEN = "Email already exists";
    private static final String WRONG_START_OR_END_DATE = "Check-in date must be before check-out date";
    private static final String CHECK_IN_DATE_CANNOT_BE_IN_THE_PAST = "Check-in date cannot be in the past";

    public void emailAlreadyExists(String email, Boolean exists) {
        if (exists) {
            throw new BusinessException(EMAIL_ALREADY_TAKEN, "Email " + email + " already exists");
        }
    }

    public void isStartDateIsBeforeEndDate(LocalDate checkIn, LocalDate checkOut) {
        if (!(checkOut == null)) {
            if (checkIn.isAfter(checkOut)) {
                throw new BusinessException(WRONG_START_OR_END_DATE, "Start date must be before end date");
            }
        }
    }

    public void isStartDateBeforeCurrentDate(LocalDate checkIn) {
        if (checkIn.isBefore(LocalDate.now())) {
            throw new BusinessException(CHECK_IN_DATE_CANNOT_BE_IN_THE_PAST, "Start date cannot be in past");
        }
    }
}
