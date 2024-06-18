package xyz.walk8243.springframework.clock.configuration;

import java.time.Clock;
import java.time.ZoneId;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class ClockConfigurationTest {

	@Nested
	@SpringBootTest(classes = ClockConfiguration.class)
	static class system {
		@Autowired
		private Clock clock;
	
		@Test
		void contextLoads() {
			Assertions.assertEquals(ZoneId.systemDefault(), clock.getZone());
		}
	}

}
