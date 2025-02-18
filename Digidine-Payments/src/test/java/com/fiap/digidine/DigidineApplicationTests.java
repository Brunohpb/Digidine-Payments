package com.fiap.digidine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = DigidineApplication.class)
@ActiveProfiles("test")
class DigidineApplicationTests {

	@Test
	void contextLoads() {
	}

}
