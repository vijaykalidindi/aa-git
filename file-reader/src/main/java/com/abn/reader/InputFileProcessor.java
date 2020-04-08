package com.abn.reader;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import com.abn.transaction.TransactionProducer;
import com.abn.transaction.TransactionService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class InputFileProcessor {

    private TransactionService transactionService;
    private TransactionProducer producer;
    private String inputFile;

    InputFileProcessor(TransactionService transactionService, TransactionProducer producer,
                       @Value("${input-file.path}") String inputFile) {
        this.transactionService = transactionService;
        this.producer = producer;
        this.inputFile = inputFile;
    }

    @Scheduled(fixedDelayString = "${input-file.process.fixedDelay}")
    void processFile() {
        try {
            File file = ResourceUtils.getFile(inputFile);
            new BufferedReader(new FileReader(file)).lines()
                    .forEach(line -> {
                        log.info("processing line >>>" + line);
                        transactionService.save(line);
                        producer.sendMessage(line);
                    });
            String newFileName = inputFile + "." + LocalDateTime.now().format(ISO_LOCAL_DATE_TIME);
            file.renameTo(new File(newFileName));
            log.info(">>>> Done. Moved " + inputFile + " to " + newFileName);
        } catch (FileNotFoundException e) {
            log.info(">>>> Waiting for file " + inputFile + " to process");
        }
    }
}
