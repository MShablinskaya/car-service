package com.innowise.sharing.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value(value = "${spring.kafka.topic.name.car}")
    private String carTopicName;
    @Value(value = "${spring.kafka.topic.name.order}")
    private String orderTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic carTopic() {
        return new NewTopic(carTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic(orderTopicName, 1, (short) 1);
    }
}
