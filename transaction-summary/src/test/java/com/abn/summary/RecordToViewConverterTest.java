package com.abn.summary;

import static com.abn.TestUtils.MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RecordToViewConverterTest {

    private RecordToViewConverter converter = new RecordToViewConverter();

    @Test
    void convert_should_return_null_when_record_is_null() {
        assertThat(converter.convert(null)).isNull();
    }

    @Test
    void convert_should_copy_values_from_record() {
        SummaryRecord record = new MessageToRecordConverter().convert(MESSAGE);
        assertThat(converter.convert(record)).isEqualToComparingFieldByField(record);
    }
}