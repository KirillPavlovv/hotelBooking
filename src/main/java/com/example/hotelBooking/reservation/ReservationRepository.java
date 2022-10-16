package com.example.hotelBooking.reservation;

import com.example.hotelBooking.roomtypes.BookedRoomsCount;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ReservationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ReservationRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<BookedRoomsCount> getBookedNumbersCounts(LocalDate checkIn, LocalDate checkOut) {
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

    public List<Integer> getFreeNumbersList(LocalDate checkIn, LocalDate checkOut, int beds) {
        return jdbcTemplate.query("""
                SELECT array_agg(number)
                FROM unnest((SELECT  array_agg(number) FROM rooms WHERE beds = :beds)::numeric[]) number
                LEFT JOIN unnest((SELECT array_agg(number) FROM rooms r JOIN reservations res on res.room = r.number
                                 WHERE ((open + interval '1' day BETWEEN :checkIn AND :checkOut)
                            OR (close - interval '1' day BETWEEN :checkIn AND :checkOut)
                            OR (open < :checkIn AND :checkOut< close)) AND r.beds =:beds)::numeric[]) room
                ON number=room
                WHERE room IS NULL;
                """, Map.of("checkIn", checkIn, "checkOut", checkOut, "beds", beds), new DataClassRowMapper<>(Integer.class) {
        });
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

    public void deleteReservation(UUID id) {
        jdbcTemplate.update("""
                DELETE FROM reservations WHERE id=:id""", Map.of("id", id));
    }
}
