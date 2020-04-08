package com.abn.consumer;

import com.abn.summary.SummaryService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
class TransactionConsumer {

    private SummaryService summaryService;

    @KafkaListener(topics = "${transactions.kafka-topic}")
    void consume(String message) throws IOException {
        log.info(String.format("#### -> Consumed message -> %s", message));
        summaryService.addToTotal(message);
    }
}
