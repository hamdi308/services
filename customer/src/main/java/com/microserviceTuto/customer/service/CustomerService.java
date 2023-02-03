package com.microserviceTuto.customer.service;

import com.microserviceTuto.customer.dao.CustomerRegistrationRequest;
import com.microserviceTuto.customer.model.Customer;
import com.microserviceTuto.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer=Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .LastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        customerRepository.save(customer);
    }
}
