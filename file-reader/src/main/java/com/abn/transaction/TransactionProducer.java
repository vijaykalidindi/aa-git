package com.abn.transaction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionProducer {
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;

    public TransactionProducer(KafkaTemplate<String, String> kafkaTemplate,
                               @Value("${transactions.kafka-topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendMessage(String message) {
        this.kafkaTemplate.send(topic, message);
        log.info(String.format("#### -> Producing message -> %s", message));
    }
}
