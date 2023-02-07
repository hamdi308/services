package com.microserviceTuto.customer.service;

import com.microserviceTuto.customer.dao.CustomerRegistrationRequest;
import com.microserviceTuto.customer.dao.FraudCheckResponse;
import com.microserviceTuto.customer.model.Customer;
import com.microserviceTuto.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService{

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer=Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .LastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse=restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerId}", FraudCheckResponse.class,customer.getId());
        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
    }
}