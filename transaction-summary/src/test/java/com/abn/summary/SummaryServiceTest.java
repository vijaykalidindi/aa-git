package com.abn.summary;

import static com.abn.TestUtils.MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class SummaryServiceTest {

    private SummaryService summaryService;
    private MessageToRecordConverter toRecordConverter;
    private RecordToViewConverter toViewConverter;

    @Mock
    private SummaryRecordRepository repository;
    @Captor
    private ArgumentCaptor<SummaryRecord> captor;

    @BeforeEach
    void setUp() {
        toRecordConverter = new MessageToRecordConverter();
        toViewConverter = new RecordToViewConverter();
        summaryService = new SummaryService(toRecordConverter, repository, toViewConverter);
    }

    @Test
    void addToTotal_should_add_entry_for_new_records() {
        SummaryRecord expected = toRecordConverter.convert(MESSAGE);
        when(repository.findByClientNumberAndProductGroupCodeAndSymbol(anyString(), anyString(), anyString()))
                .thenReturn(null);

        summaryService.addToTotal(MESSAGE);

        verify(repository).save(captor.capture());
        assertThat(captor.getValue()).isEqualToComparingFieldByField(expected);
    }

    @Test
    void addToTotal_should_update_total_for_existing_records() {
        SummaryRecord existing = toRecordConverter.convert(MESSAGE);
        when(repository.findByClientNumberAndProductGroupCodeAndSymbol(anyString(), anyString(), anyString()))
                .thenReturn(existing);

        summaryService.addToTotal(MESSAGE);

        verify(repository).save(captor.capture());
        assertThat(captor.getValue()).isEqualToComparingFieldByField(existing);
        assertThat(captor.getValue().getTotalTransactionAmount()).isEqualTo(BigDecimal.valueOf(2L));
    }

    @Test
    void getSummaryViews_should_return_views() {
        SummaryRecord existing = toRecordConverter.convert(MESSAGE);
        when(repository.findAll()).thenReturn(Collections.singletonList(existing));

        List<View> views = summaryService.getSummaryViews();

        assertThat(views).hasSize(1);
        assertThat(views.get(0)).isEqualToComparingFieldByField(existing);
    }

    @Test
    void getCsvRecords_should_return_views() {
        SummaryRecord existing = toRecordConverter.convert(MESSAGE);
        when(repository.findAll()).thenReturn(Collections.singletonList(existing));

        Stream<View> csvRecords = summaryService.getCsvRecords();

        List<View> collect = csvRecords.collect(Collectors.toList());
        assertThat(collect).hasSize(1);
        assertThat(collect.get(0)).isEqualToComparingFieldByField(existing);
    }

}