package com.innowise.sharing.kafka.producer.impl;

import com.innowise.sharing.entity.Car;
import com.innowise.sharing.kafka.mapper.RecordMapper;
import com.innowise.sharing.kafka.producer.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@Slf4j
@Component("carMessageProducer")
@RequiredArgsConstructor
public class CarMessageProducerImpl implements MessageProducer<Car> {
    private static final String SCHEMA_FILE_PATH = "kafka/avro/car-schema.avsc";
    private final KafkaTemplate<String, GenericRecord> kafkaTemplate;
    private final RecordMapper<Car> carRecordMapper;
    @Value(value = "${spring.kafka.topic.name.car}")
    private String topicName;

    @Override
    public void send(Car car) {
        final File schemaFile;
        final Schema schema;
        try {
            schemaFile = Paths.get(ClassLoader.getSystemResource(SCHEMA_FILE_PATH).toURI()).toFile();
            schema = new Schema.Parser().parse(schemaFile);
        } catch (URISyntaxException | IOException exception) {
            log.error("Error has occurred with extracting car avro schema: {}", exception.getMessage());
            return;
        }
        GenericRecord carRecord = carRecordMapper.mapToRecord(car, schema);
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
