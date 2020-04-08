package com.abn.transaction;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionService {
    private TransactionRepository repository;

    public Transaction save(final String line) {
        return repository.save(Transaction.builder()
                .transaction(line)
                .build());
    }
}
