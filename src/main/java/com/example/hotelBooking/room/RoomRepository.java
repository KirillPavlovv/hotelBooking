package com.example.hotelBooking.room;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RoomRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RoomRepository(NamedParameterJdbcTemplate jdbcTemplate, JdbcTemplate jT) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getRoomsCount(int beds)  {
      return jdbcTemplate.queryForObject("SELECT COUNT(*) AS total_rooms FROM rooms WHERE beds = :beds;", Map.of("beds", beds), Integer.class);
    }
}
