package com.microserviceTuto.fraud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Document(collection = "FraudCheckHistory")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FraudCheckHistory {
    @Transient
    public static final String SEQUENCE_NAME = "fraud_sequence";
    @Id
    private Integer id;
    @Indexed(unique = true)
    @Field(value = "customerId")
    private Integer customerId;
    @Field(value = "isFraudster")
    private boolean isFraudster;
    @Field(value = "createdAt")
    private LocalDate createdAt;

}
