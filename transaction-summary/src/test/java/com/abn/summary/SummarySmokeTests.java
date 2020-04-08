package com.abn.summary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SummarySmokeTests {

	@Autowired
	private SummaryController summaryController;

	@Test
	void contextLoads() {
		assertThat(summaryController).isNotNull();
	}

}
