package com.microserviceTuto.fraud.sequence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

@Document(collection = "db_sequence")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DbSequence {
    @Id
    String id;
    @Field("sequence_number")
    int sequence;
}