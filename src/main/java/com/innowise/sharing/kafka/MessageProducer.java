package com.innowise.sharing.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class MessageProducer {
    private static final Logger LOGGER = LogManager.getLogger(MessageProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value(value = "${spring.kafka.topic.name}")
    private String topicName;

    @Autowired
    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String data) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, data);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.info("Sent message=[{}]", data);
            }

            @Override
            public void onFailure(Throwable throwable) {
                LOGGER.error("Unable to send message=[{}}] due to : {}", data, throwable.getMessage());
            }
        });
    }
}
