package com.abn.summary;

import static com.abn.TestUtils.MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class MessageToRecordConverterTest {

    private MessageToRecordConverter converter = new MessageToRecordConverter();

    @Test
    void convert_should_return_null_for_null_message() {
        SummaryRecord summaryRecord = converter.convert(null);
        assertThat(summaryRecord).isNull();
    }

    @Test
    void convert_should_convert_message_to_SummaryRecord() {
        SummaryRecord summaryRecord = converter.convert(MESSAGE);
        assertThat(summaryRecord.getClientType()).isEqualTo("CL");
        assertThat(summaryRecord.getClientNumber()).isEqualTo("4321");
        assertThat(summaryRecord.getAccountNumber()).isEqualTo("0002");
        assertThat(summaryRecord.getSubAccountNumber()).isEqualTo("0001");
        assertThat(summaryRecord.getExchangeCode()).isEqualTo("SGX");
        assertThat(summaryRecord.getProductGroupCode()).isEqualTo("FU");
        assertThat(summaryRecord.getSymbol()).isEqualTo("NK");
        assertThat(summaryRecord.getExpirationDate()).isEqualTo("20100910");
        assertThat(summaryRecord.getTotalTransactionAmount()).isEqualTo(BigDecimal.ONE);
    }

}