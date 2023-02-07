package com.microserviceTuto.fraud.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DbSequenceGenr {
    @Autowired
    private MongoOperations operations;

    public int getNextSequence(final String sequenceName) {
        final Query q = new Query(Criteria.where("id").is(sequenceName));
        final Update u = new Update().inc("sequence", 1);
        final DbSequence counter = operations.findAndModify(q, u,
                FindAndModifyOptions.options().returnNew(true).upsert(true), DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSequence() : 1;
    }
}
