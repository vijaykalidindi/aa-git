package com.abn.transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
class TransactionProducerTest {
    private TransactionProducer transactionProducer;
    private String topic = "topic";

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;
    @Captor
    private ArgumentCaptor<String> captor;

    @BeforeEach
    void setUp() {
        transactionProducer = new TransactionProducer(kafkaTemplate, topic);
    }

    @Test
    void sendMessage_should_call_template_send_to_the_given_topic() {
        //When
        transactionProducer.sendMessage("message");

        //Then
        verify(kafkaTemplate).send(captor.capture(), captor.capture());
        assertThat(captor.getAllValues()).containsExactly(topic, "message");
    }
}