package com.microserviceTuto.fraud.controller;

import com.microserviceTuto.fraud.dao.FraudCheckResponse;
import com.microserviceTuto.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {
    @Autowired
    private FraudCheckService fraudCheckService;
    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isFraudulentCustomer=fraudCheckService.isFraudulentCustomer(customerId);
        return FraudCheckResponse.builder()
                .isFraudster(isFraudulentCustomer)
                .build();
    }
}
