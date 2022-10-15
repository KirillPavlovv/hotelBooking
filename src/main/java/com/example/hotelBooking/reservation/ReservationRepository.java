package com.example.hotelBooking.reservation;

import com.example.hotelBooking.roomtypes.BookedRoomsCount;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ReservationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ReservationRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<BookedRoomsCount> getBookedNumbers(LocalDate checkIn, LocalDate checkOut) {
        return jdbcTemplate.query("""
                        SELECT beds, count(beds) AS booked_rooms FROM rooms r
                        JOIN reservations res on res.room = r.number
                        WHERE (open + interval '1' day BETWEEN  :checkIn AND :checkOut)
                           OR (close - interval '1' day BETWEEN :checkIn AND :checkOut)
                        OR (open < :checkIn AND :checkOut < close)
                        GROUP BY beds
                        ORDER BY beds;""", Map.of("checkIn", checkIn, "checkOut", checkOut),
                new DataClassRowMapper<>(BookedRoomsCount.class));
    }

    public List<ReservationResponse> getReservations(LocalDate today) {
        return jdbcTemplate.query("""
                SELECT r.id, first_name AS customerFirstName, last_name AS customerLastName, r.room AS roomNumber,
                 r.open AS checkIn, r.close AS checkOut FROM customers c
                    JOIN reservations r ON (c.id=r.customer_id)
                    WHERE close >= DATE '2022-10-10'
                    ORDER BY r.room, r.open;
                """, Map.of("today", today), new DataClassRowMapper<>(ReservationResponse.class));

    }
}
