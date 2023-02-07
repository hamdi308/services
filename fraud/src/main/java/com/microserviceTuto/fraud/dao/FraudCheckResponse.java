package com.microserviceTuto.fraud.dao;

import lombok.Builder;

@Builder
public record FraudCheckResponse(Boolean isFraudster) {
}
