package com.abn.transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    private TransactionService transactionService;
    @Mock
    private TransactionRepository transactionRepository;
    @Captor
    private ArgumentCaptor<Transaction> captor;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    void save_should_call_db_save() {
        //When
        transactionService.save("test");

        //Then
        verify(transactionRepository).save(captor.capture());
        assertThat(captor.getValue().getTransaction()).isEqualTo("test");
    }
}