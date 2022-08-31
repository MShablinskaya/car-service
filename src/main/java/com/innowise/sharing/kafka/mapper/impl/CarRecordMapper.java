package com.innowise.sharing.kafka.mapper.impl;

import com.innowise.sharing.entity.Car;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.kafka.mapper.RecordMapper;
import lombok.RequiredArgsConstructor;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.springframework.stereotype.Component;

import static com.innowise.sharing.kafka.mapper.AvroRecordField.AVAILABILITY;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.BRAND;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.COLOR;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.ID;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.MODEL;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.USER;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.REGISTRATION_NUMBER;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.RELEASE_YEAR;

@Component
@RequiredArgsConstructor
public class CarRecordMapper implements RecordMapper<Car> {
    private final RecordMapper<User> ownerRecordMapper;

    @Override
    public GenericRecord mapToRecord(Car car, Schema schema) {
        GenericRecord ownerRecord = ownerRecordMapper.mapToRecord(car.getOwner(), schema);
        return new GenericRecordBuilder(schema)
                .set(ID.getField(), car.getId())
                .set(BRAND.getField(), car.getBrand())
                .set(MODEL.getField(), car.getModel())
                .set(COLOR.getField(), car.getColor())
                .set(RELEASE_YEAR.getField(), car.getReleaseYear())
                .set(REGISTRATION_NUMBER.getField(), car.getRegistrationNumber())
                .set(AVAILABILITY.getField(), car.getAvailability())
                .set(USER.getField(), ownerRecord)
                .build();
    }
}
