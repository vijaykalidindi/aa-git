package com.abn.summary;


import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class SummaryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;
    private String exchangeCode;
    private String productGroupCode;
    private String symbol;
    private String expirationDate;
    @Setter
    private BigDecimal totalTransactionAmount;
}
