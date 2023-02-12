package com.microserviceTuto.fraud.controller;

import com.microserviceTuto.fraud.dao.FraudCheckResponse;
import com.microserviceTuto.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {
    @Autowired
    private FraudCheckService fraudCheckService;
    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isFraudulentCustomer=fraudCheckService.isFraudulentCustomer(customerId);
        log.info("Fraud Check request for customer {}",customerId);
        return FraudCheckResponse.builder()
                .isFraudster(isFraudulentCustomer)
                .build();
    }

}