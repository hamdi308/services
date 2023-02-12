package com.microserviceTuto.customer.controller;

import com.microserviceTuto.customer.dao.CustomerRegistrationRequest;
import com.microserviceTuto.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("new customer registration {}",customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }
}
