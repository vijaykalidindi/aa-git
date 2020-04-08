package com.abn.summary;

import com.opencsv.bean.CsvBindByPosition;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class View {
    @CsvBindByPosition(position = 0)
    private String clientType;
    @CsvBindByPosition(position = 1)
    private String clientNumber;
    @CsvBindByPosition(position = 2)
    private String accountNumber;
    @CsvBindByPosition(position = 3)
    private String subAccountNumber;
    @CsvBindByPosition(position = 4)
    private String exchangeCode;
    @CsvBindByPosition(position = 5)
    private String productGroupCode;
    @CsvBindByPosition(position = 6)
    private String symbol;
    @CsvBindByPosition(position = 7)
    private String expirationDate;
    @CsvBindByPosition(position = 8)
    private BigDecimal totalTransactionAmount;
}
