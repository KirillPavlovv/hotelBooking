package com.example.hotelBooking.customer;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CustomerRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveCustomer(Customer customer) {
        jdbcTemplate.update("""
                INSERT INTO customers(id, first_name, last_name, personal_code, email)
                VALUES(:id, :firstName, :lastName, :personalCode, :email);
                """, Map.of("id", customer.getId(), "firstName", customer.getFirstName(), "lastName", customer.getLastName(),
                "personalCode", customer.getPersonalCode(), "email", customer.getEmail()));
    }
}
