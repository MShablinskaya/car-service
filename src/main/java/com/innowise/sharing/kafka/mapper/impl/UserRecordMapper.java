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
import static com.innowise.sharing.kafka.mapper.AvroRecordField.DOCUMENT;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.USER;

@Component
@RequiredArgsConstructor
public class UserRecordMapper implements RecordMapper<User> {
    private final RecordMapper<Document> documentRecordMapper;

    @Override
    public GenericRecord mapToRecord(User user, Schema schema) {
        Schema userSchema = schema.getField(USER.getField()).schema();
        GenericRecord documentRecord = documentRecordMapper.mapToRecord(user.getLicence(), userSchema);
        return new GenericRecordBuilder(userSchema)
                .set(FIRST_NAME.getField(), user.getFirstName())
                .set(LAST_NAME.getField(), user.getLastName())
                .set(EMAIL.getField(), user.getEmail())
                .set(DOCUMENT.getField(), documentRecord)
                .build();
    }
}
