package com.abn.reader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.linesOf;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.abn.transaction.TransactionProducer;
import com.abn.transaction.TransactionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class InputFileProcessorTest {

    @Mock
    private TransactionService transactionService;
    @Mock
    private TransactionProducer transactionProducer;
    @Captor
    private ArgumentCaptor<String> dbCaptor;
    @Captor
    private ArgumentCaptor<String> streamCaptor;

    private InputFileProcessor fileProcessor;
    private final String sourceDataFile = "src/test/resources/Inputtest.txt";
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = createFileForTest();
        fileProcessor = new InputFileProcessor(transactionService, transactionProducer, tempFile.getPath());
    }

    @Test
    void processFile_should_process_each_line_and_save_to_db_and_stream() {
        //Given
        List<String> expectedLines = linesOf(tempFile);

        //When
        fileProcessor.processFile();

        //Then
        verify(transactionService, times(expectedLines.size())).save(dbCaptor.capture());
        verify(transactionProducer, times(expectedLines.size())).sendMessage(streamCaptor.capture());
        assertThat(dbCaptor.getAllValues()).containsExactlyElementsOf(expectedLines);
        assertThat(streamCaptor.getAllValues()).containsExactlyElementsOf(expectedLines);
        assertThat(tempFile.exists()).isFalse();
    }

    private File createFileForTest() throws IOException {
        File tempFile = File.createTempFile("test", ".tmp");
        Files.write(tempFile.toPath(), linesOf(new File(sourceDataFile)));
        tempFile.deleteOnExit();
        return tempFile;
    }
}