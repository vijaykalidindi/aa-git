package com.abn.summary;

import org.springframework.data.repository.CrudRepository;

interface SummaryRecordRepository extends CrudRepository<SummaryRecord, Long> {
    SummaryRecord findByClientNumberAndProductGroupCodeAndSymbol(String clientNumber, String productGroupCode, String symbol);
}
