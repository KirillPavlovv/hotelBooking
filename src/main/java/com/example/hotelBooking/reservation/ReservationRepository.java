package com.example.hotelBooking.reservation;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class ReservationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ReservationRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UUID saveNewReservation(Reservation reservation) {
        UUID id = UUID.randomUUID();
        reservation.setId(id);
        jdbcTemplate.update("INSERT INTO reservations (id, customer_id, room, open, close) " +
                "VALUES (:id, :customerId, :room, :open, :close)", new BeanPropertySqlParameterSource(reservation));
        return id;
    }

    public void updateReservation(Reservation reservation) {
        jdbcTemplate.update("UPDATE reservations SET customer_id=:customerId, room=:room, open=:open, close=:close " +
                "WHERE id=:id", new BeanPropertySqlParameterSource(reservation));
    }

    public void deleteReservation(UUID id) {
        jdbcTemplate.update("DELETE FROM reservations WHERE id=:id", Map.of("id", id));
    }
}
