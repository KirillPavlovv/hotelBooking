package com.example.hotelBooking.reservation;

import com.example.hotelBooking.room.Room;
import com.example.hotelBooking.roomtypes.BookedRoomsCount;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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

    public List<Room> getFreeNumbersList(LocalDate checkIn, LocalDate checkOut, int beds) {
        return jdbcTemplate.query("""
                SELECT *
                FROM unnest((SELECT  array_agg(number) FROM rooms WHERE beds = :beds)) number
                LEFT JOIN unnest((SELECT array_agg(number) FROM rooms r JOIN reservations res on res.room = r.number
                                 WHERE ((open + interval '1' day BETWEEN :checkIn AND :checkOut)
                            OR (close - interval '1' day BETWEEN :checkIn AND :checkOut)
                            OR (open < :checkIn AND :checkOut< close)) AND r.beds =:beds)) room
                ON number=room
                WHERE room IS NULL;
                """, Map.of("checkIn", checkIn, "checkOut", checkOut, "beds", beds), new DataClassRowMapper<>(Room.class) {
        });
    }

    public List<ReservationResponse> getReservations(LocalDate today) {
        return jdbcTemplate.query("""
                SELECT r.id, first_name AS customerFirstName, last_name AS customerLastName, rm.beds AS roomType, r.room AS roomNumber,
                       r.open AS checkIn, r.close AS checkOut
                FROM reservations r
                    JOIN customers c ON c.id=r.customer_id
                    JOIN rooms rm ON r.room = rm.number
                WHERE close >= :today
                ORDER BY r.room, r.open;
                """, Map.of("today", today), new DataClassRowMapper<>(ReservationResponse.class));

    }

    public void deleteReservation(UUID id) {
        jdbcTemplate.update("""
                DELETE FROM reservations WHERE id=:id""", Map.of("id", id));
    }

    public void saveReservation(Reservation reservation) {
        jdbcTemplate.update("""
                INSERT INTO reservations(id, customer_id, room, open, close)
                VALUES(:id, :customerId, :room, :open, :close);
                """, Map.of("id", reservation.getId(), "customerId", reservation.getCustomerId(),
                "room", reservation.getRoom(), "open", reservation.getOpen(), "close", reservation.getClose()));
    }

    public void updateReservation(Reservation reservation) {
        jdbcTemplate.update("""
                UPDATE reservations SET open=:open, close=:close, room=:room
                WHERE id=:id
                """, new BeanPropertySqlParameterSource(reservation));
    }
}
