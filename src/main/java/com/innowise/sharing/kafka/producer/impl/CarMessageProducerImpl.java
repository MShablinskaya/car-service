package com.innowise.sharing.kafka.producer.impl;

import com.innowise.sharing.entity.Car;
import com.innowise.sharing.kafka.producer.MessageProducer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.File;

@Slf4j
@Component
public class CarMessageProducerImpl implements MessageProducer<Car> {
    private static final String FILE_PATH =
            "G:\\Work\\Innowise\\AutoSharing\\car-service\\src\\main\\resources\\kafka.avro\\car-schema.avsc";
    private final KafkaTemplate<String, GenericRecord> kafkaTemplate;
    @Value(value = "${spring.kafka.topic.name}")
    private String topicName;

    @Autowired
    public CarMessageProducerImpl(KafkaTemplate<String, GenericRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @SneakyThrows
    @Override
    public void send(Car car) {
        Schema schema = new Schema.Parser().parse(new File(FILE_PATH));
        GenericRecord ownerRecord = new GenericRecordBuilder(schema.getField("owner").schema())
                .set("first_name", car.getOwnerId().getFirstName())
                .set("last_name", car.getOwnerId().getLastName())
                .set("email", car.getOwnerId().getEmail())
                .build();
        GenericRecord carRecord = new GenericRecordBuilder(schema)
                .set("id", car.getId())
                .set("brand", car.getBrand())
                .set("model", car.getModel())
                .set("color", car.getColor())
                .set("release_year", car.getReleaseYear().getYear())
                .set("registration_number", car.getRegistrationNumber())
                .set("availability", car.getAvailability())
                .set("owner", ownerRecord)
                .build();
        ProducerRecord<String, GenericRecord> producerRecord = new ProducerRecord<>(topicName, carRecord);
        ListenableFuture<SendResult<String, GenericRecord>> future = kafkaTemplate.send(producerRecord);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, GenericRecord> result) {
                log.info("Car data was successfully sent.");
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.error("Unable to send car data due to : {}", throwable.getMessage());
            }
        });
    }
}
