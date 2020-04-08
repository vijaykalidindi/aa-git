package com.abn.summary;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SummaryController.class)
public class SummaryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SummaryService summaryService;

    @Test
    public void getSummary_should_return_200_with_empty_json() throws Exception {
        this.mockMvc.perform(get("/summary"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }


    @Test
    public void getSummaryCsv_should_return_200_with_empty_csv() throws Exception {
        this.mockMvc.perform(get("/summary-export").accept("text/csv"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_DISPOSITION, "attachment; filename=\"Output.csv\""))
                .andExpect(content().contentType("text/csv"));
    }
}
