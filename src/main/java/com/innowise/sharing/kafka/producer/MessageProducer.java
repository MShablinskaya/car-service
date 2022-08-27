package com.innowise.sharing.kafka.producer;

public interface MessageProducer<T> {
    void send(T t);
}
