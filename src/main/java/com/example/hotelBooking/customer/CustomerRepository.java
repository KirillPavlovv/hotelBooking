package com.example.hotelBooking.customer;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    public CustomerRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UUID saveNewUser(Customer customer) {
        UUID id = UUID.randomUUID();
        customer.setId(id);
        jdbcTemplate.update("INSERT INTO customers (id, first_name, last_name, personal_code, email) " +
                "VALUES (:id, :firstName, :lastName, :personalCode, :email)", new BeanPropertySqlParameterSource(customer));
        return id;
    }
}
