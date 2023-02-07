package com.microserviceTuto.fraud.repository;

import com.microserviceTuto.fraud.model.FraudCheckHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckRepository extends MongoRepository<FraudCheckHistory,Integer> {
}
