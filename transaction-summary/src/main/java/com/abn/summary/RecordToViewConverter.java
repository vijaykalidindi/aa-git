package com.abn.summary;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecordToViewConverter implements Converter<SummaryRecord, View> {
    @Override
    public View convert(SummaryRecord record) {
        return record == null ? null :
                View.builder()
                        .clientType(record.getClientType())
                        .clientNumber(record.getClientNumber())
                        .accountNumber(record.getAccountNumber())
                        .subAccountNumber(record.getSubAccountNumber())
                        .exchangeCode(record.getExchangeCode())
                        .productGroupCode(record.getProductGroupCode())
                        .symbol(record.getSymbol())
                        .expirationDate(record.getExpirationDate())
                        .totalTransactionAmount(record.getTotalTransactionAmount())
                        .build();
    }
}
