package com.example.hotelBooking.customer;

import com.example.hotelBooking.reservation.ReservationRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class CustomerService {

    @Resource
    CustomerRepository customerRepository;


    public Customer saveCustomer(ReservationRequest reservationRequest) {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setFirstName(reservationRequest.getFirstName());
        customer.setLastName(reservationRequest.getLastName());
        customer.setPersonalCode(reservationRequest.getPersonalCode());
        customer.setEmail(reservationRequest.getEmail());
        customerRepository.saveCustomer(customer);
        return customer;
    }

    public Customer updateCustomer(ReservationRequest reservationRequest) {
        Customer customer = customerRepository.findByPersonalCode(reservationRequest.getPersonalCode());
        customerRepository.updateCustomer(reservationRequest, customer.getPersonalCode());

        return customer;
    }
}
