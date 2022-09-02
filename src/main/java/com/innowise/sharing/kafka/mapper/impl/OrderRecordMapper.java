package com.innowise.sharing.kafka.mapper.impl;

import com.innowise.sharing.entity.Car;
import com.innowise.sharing.entity.Order;
import com.innowise.sharing.entity.User;
import com.innowise.sharing.kafka.mapper.RecordMapper;
import lombok.RequiredArgsConstructor;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.springframework.stereotype.Component;

import static com.innowise.sharing.kafka.mapper.AvroRecordField.BOOKING_DATE;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.CAR;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.ID;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.RETURN_DATE;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.STATE;
import static com.innowise.sharing.kafka.mapper.AvroRecordField.USER;

@Component
@RequiredArgsConstructor
public class OrderRecordMapper implements RecordMapper<Order> {
    private final RecordMapper<User> customerRecordMapper;
    private final RecordMapper<Car> carRecordMapper;

    @Override
    public GenericRecord mapToRecord(Order order, Schema schema) {
        GenericRecord carRecord = carRecordMapper.mapToRecord(order.getCar(), schema);
        GenericRecord customerRecord = customerRecordMapper.mapToRecord(order.getCustomer(), schema);
        return new GenericRecordBuilder(schema)
                .set(ID.getField(), order.getId())
                .set(BOOKING_DATE.getField(), order.getBookingDate().toString())
                .set(RETURN_DATE.getField(), order.getReturnDate().toString())
                .set(STATE.getField(), order.getState().toString().toLowerCase())
                .set(USER.getField(), customerRecord)
                .set(CAR.getField(), carRecord)
                .build();
    }
}
