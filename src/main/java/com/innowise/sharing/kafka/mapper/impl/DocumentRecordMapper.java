package com.innowise.sharing.kafka.mapper.impl;

import com.innowise.sharing.entity.Document;
import com.innowise.sharing.kafka.mapper.RecordMapper;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.springframework.stereotype.Component;

import static com.innowise.sharing.kafka.mapper.AvroRecordField.DOCUMENT_TYPE;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.EXPIRATION_DATE;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.DOCUMENT;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.LICENSE_NUMBER;

@Component
public class DocumentRecordMapper implements RecordMapper<Document> {
    @Override
    public GenericRecord mapToRecord(Document document, Schema schema) {
        return new GenericRecordBuilder(schema.getField(DOCUMENT.getField()).schema())
                .set(LICENSE_NUMBER.getField(), document.getLicenseNumber())
                .set(EXPIRATION_DATE.getField(), document.getExpirationDate().toString())
                .set(DOCUMENT_TYPE.getField(), document.getType().toString().toLowerCase())
                .build();
    }
}
