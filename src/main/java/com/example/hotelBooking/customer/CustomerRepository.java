package com.example.hotelBooking.customer;

import com.example.hotelBooking.reservation.ReservationRequest;
import org.springframework.jdbc.core.DataClassRowMapper;
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

    public Customer findByPersonalCode(String personalCode) {
        return jdbcTemplate.queryForObject("""
                SELECT * FROM customers WHERE personal_code=:personalCode
                """, Map.of("personalCode", personalCode), new DataClassRowMapper<>(Customer.class));
    }

    public Boolean customerExists(String personalCode) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT FROM customers WHERE personal_code = :personalCode)", Map.of("personalCode", personalCode), Boolean.class);
    }

    public void updateCustomer(ReservationRequest reservationRequest, String personalCode) {
        jdbcTemplate.update("""
                UPDATE customers SET first_name=:firstName, last_name=:lastName, email=:email
                WHERE personal_code=:personalCode
                """, Map.of("firstName", reservationRequest.getFirstName(), "lastName", reservationRequest.getLastName(),
                "email", reservationRequest.getEmail(), "personalCode", personalCode));
    }
}
