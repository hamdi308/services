package com.microserviceTuto.fraud.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckRequest {
    public Integer customerId;
    public Boolean isFraudster;
}