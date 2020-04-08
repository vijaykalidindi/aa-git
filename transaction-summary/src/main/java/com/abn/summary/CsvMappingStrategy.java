package com.abn.summary;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CsvMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {

    private final static String[] HEADER = new String[]{"CLIENT TYPE",
            "CLIENT NUMBER", "ACCOUNT NUMBER", "SUBACCOUNT NUMBER", "EXCHANGE CODE",
            "PRODUCT GROUP CODE", "SYMBOL", "EXPIRATION DATE", "TOTAL TRANSACTION AMOUNT"};

    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
        super.generateHeader(bean);
        return HEADER;
    }
}
