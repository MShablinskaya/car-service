package com.innowise.sharing.kafka.producer.impl;

import com.innowise.sharing.entity.Order;
import com.innowise.sharing.kafka.mapper.RecordMapper;
import com.innowise.sharing.kafka.producer.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
import java.nio.file.Paths;

@Slf4j
@Component("orderMessageProducer")
@RequiredArgsConstructor
public class OrderMessageProducerImpl implements MessageProducer<Order> {
    private static final String SCHEMA_FILE_PATH = "kafka/avro/order-schema.avsc";
    private final KafkaTemplate<String, GenericRecord> kafkaTemplate;
    private final RecordMapper<Order> orderRecordMapper;
    @Value(value = "${spring.kafka.topic.name.order}")
    private String topicName;

    @SneakyThrows
    @Override
    public void send(Order order) {
        final File schemaFile = Paths.get(ClassLoader.getSystemResource(SCHEMA_FILE_PATH).toURI()).toFile();
        final Schema schema = new Schema.Parser().parse(schemaFile);
        GenericRecord orderRecord = orderRecordMapper.mapToRecord(order, schema);
        ProducerRecord<String, GenericRecord> producerRecord = new ProducerRecord<>(topicName, orderRecord);
        ListenableFuture<SendResult<String, GenericRecord>> future = kafkaTemplate.send(producerRecord);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, GenericRecord> result) {
                log.info("Order data was successfully sent.");
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.error("Unable to send order data due to : {}", throwable.getMessage());
            }
        });
    }
}
