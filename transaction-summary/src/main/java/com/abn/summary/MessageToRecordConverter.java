package com.abn.summary;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class MessageToRecordConverter implements Converter<String, SummaryRecord> {
    @Override
    public SummaryRecord convert(String message) {
        if (message == null) {
            return null;
        }
        return SummaryRecord.builder()
                .clientType(message.substring(3, 7).trim())
                .clientNumber(message.substring(7, 11).trim())
                .accountNumber(message.substring(11, 15).trim())
                .subAccountNumber(message.substring(15, 19).trim())
                .exchangeCode(message.substring(27, 31).trim())
                .productGroupCode(message.substring(25, 27).trim())
                .symbol(message.substring(31, 37).trim())
                .expirationDate(message.substring(37, 45).trim())
                .totalTransactionAmount(getTransactionAmount(message))
                .build();
    }

    private BigDecimal getTransactionAmount(String message) {
        return new BigDecimal(message.substring(52, 62).trim())
                .subtract(new BigDecimal(message.substring(63, 73).trim()));
    }
}
