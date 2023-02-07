package com.microserviceTuto.customer.dao;

import lombok.Builder;

@Builder
public record FraudCheckResponse(Boolean isFraudster) {
}