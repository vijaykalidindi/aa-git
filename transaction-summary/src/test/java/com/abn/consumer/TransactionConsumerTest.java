package com.abn.consumer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.abn.summary.SummaryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class TransactionConsumerTest {

    private TransactionConsumer transactionConsumer;
    private SummaryService summaryService;

    @BeforeEach
    void setUp() {
        summaryService = mock(SummaryService.class);
        transactionConsumer = new TransactionConsumer(summaryService);
    }

    @Test
    void consume_should_convert_and_add_call_service() throws IOException {
        transactionConsumer.consume("message");
        verify(summaryService).addToTotal("message");
    }
}