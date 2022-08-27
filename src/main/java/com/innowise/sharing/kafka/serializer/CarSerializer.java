package com.innowise.sharing.kafka.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.sharing.entity.Car;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public class CarSerializer implements Serializer<Car> {
    @SneakyThrows
    @Override
    public byte[] serialize(String topic, Car car) {
        return new ObjectMapper().writeValueAsString(car).getBytes();
    }
}
