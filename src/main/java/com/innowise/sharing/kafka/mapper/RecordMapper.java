package com.innowise.sharing.kafka.mapper;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

public interface RecordMapper<T> {
    GenericRecord mapToRecord(T t, Schema schema);
}
