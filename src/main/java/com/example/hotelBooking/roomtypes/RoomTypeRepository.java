package com.example.hotelBooking.roomtypes;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RoomTypeRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RoomType> getRoomTypes() {
        return jdbcTemplate.query("""
                SELECT * FROM room_types""", new DataClassRowMapper<>(RoomType.class));
    }
}