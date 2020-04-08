package com.abn.summary;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SummaryService {

    private MessageToRecordConverter converter;
    private SummaryRecordRepository repository;
    private RecordToViewConverter toViewConverter;

    public void addToTotal(String message) {
        SummaryRecord record = converter.convert(message);
        SummaryRecord existing = repository.findByClientNumberAndProductGroupCodeAndSymbol(record.getClientNumber(),
                record.getProductGroupCode(), record.getSymbol());
        if (existing == null) {
            repository.save(record);
        } else {
            existing.setTotalTransactionAmount(existing.getTotalTransactionAmount().add(record.getTotalTransactionAmount()));
            repository.save(existing);
        }
    }

    List<View> getSummaryViews() {
        return StreamSupport.stream(repository.findAll().spliterator(), true)
                .map(record -> toViewConverter.convert(record))
                .collect(Collectors.toList());
    }

    Stream<View> getCsvRecords() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(record ->toViewConverter.convert(record));
    }
}
