package com.abn.summary;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
class SummaryController {

    private SummaryService summaryService;
    private static final String OUTPUT_FILE = "Output.csv";

    @GetMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<View> getSummary() {
        return summaryService.getSummaryViews();
    }

    @GetMapping(value = "/summary-export")
    @ResponseStatus(HttpStatus.OK)
    void getSummaryCsv(HttpServletResponse response) {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + OUTPUT_FILE + "\"");
        CsvMappingStrategy<View> mappingStrategy = new CsvMappingStrategy<>();
        mappingStrategy.setType(View.class);
        try {
            StatefulBeanToCsv<View> writer = new StatefulBeanToCsvBuilder<View>(response.getWriter())
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            writer.write(summaryService.getCsvRecords());
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }
}
