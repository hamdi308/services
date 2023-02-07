package com.microserviceTuto.fraud.service;

import com.microserviceTuto.fraud.dao.FraudCheckRequest;
import com.microserviceTuto.fraud.model.FraudCheckHistory;
import com.microserviceTuto.fraud.repository.FraudCheckRepository;
import com.microserviceTuto.fraud.sequence.DbSequenceGenr;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {
    @Autowired
    private final FraudCheckRepository fraudCheckRepository;
    @Autowired
    private DbSequenceGenr sequenceGenr;
    public boolean isFraudulentCustomer(Integer customerId){
        fraudCheckRepository.save(FraudCheckHistory.builder()
                .id(sequenceGenr.getNextSequence(FraudCheckHistory.SEQUENCE_NAME))
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDate.from(LocalDateTime.now()))
                .build());
        return false;
    }

}