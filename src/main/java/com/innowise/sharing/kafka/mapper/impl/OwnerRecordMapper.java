package com.innowise.sharing.kafka.mapper.impl;

import com.innowise.sharing.entity.Document;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.kafka.mapper.RecordMapper;
import lombok.RequiredArgsConstructor;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.springframework.stereotype.Component;

import static com.innowise.sharing.kafka.mapper.AvroRecordField.EMAIL;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.FIRST_NAME;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.LAST_NAME;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.LICENSE;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.OWNER;

@Component
@RequiredArgsConstructor
public class OwnerRecordMapper implements RecordMapper<User> {
    private final RecordMapper<Document> documentRecordMapper;

    @Override
    public GenericRecord mapToRecord(User owner, Schema schema) {
        GenericRecord documentRecord = documentRecordMapper.mapToRecord(owner.getLicenceId(), schema);
        return new GenericRecordBuilder(schema.getField(OWNER.getField()).schema())
                .set(FIRST_NAME.getField(), owner.getFirstName())
                .set(LAST_NAME.getField(), owner.getLastName())
                .set(EMAIL.getField(), owner.getEmail())
                .set(LICENSE.getField(), documentRecord)
                .build();
    }
}
