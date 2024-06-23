package xyz.walk8243.springframework.clock.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

class ClockConfigurationTest {

	@Nested
	@SpringBootTest(classes = ClockConfiguration.class)
	static class NoProperties {
		@Autowired
		private Clock clock;
	
		@Test
		void contextLoads() {
			assertEquals(ZoneId.systemDefault(), clock.getZone());
			assertEquals(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.now(clock).format(DateTimeFormatter.ISO_DATE_TIME));
		}
	}

	@Nested
	@SpringBootTest(classes = ClockConfiguration.class)
	@TestPropertySource(properties = {
		"spring.clock.timezone-id: Europe/London",
	})
	static class OnlyTimezone {
		@Autowired
		private Clock clock;
	
		@Test
		void contextLoads() {
			final ZoneId zoneId = ZoneId.of("Europe/London");
			assertEquals(zoneId, clock.getZone());
			assertEquals(LocalDateTime.now(zoneId).format(DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.now(clock).format(DateTimeFormatter.ISO_DATE_TIME));
		}
	}

	@Nested
	@SpringBootTest(classes = ClockConfiguration.class)
	@TestPropertySource(properties = {
		"spring.clock.mock-datetime: 2007-12-03T10:15:30",
	})
	static class OnlyMockDatetime {
		@Autowired
		private Clock clock;
	
		@Test
		void contextLoads() {
			assertEquals(ZoneId.systemDefault(), clock.getZone());
			assertEquals("2007-12-03T10:15:30", LocalDateTime.now(clock).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		}
	}

	@Nested
	@SpringBootTest(classes = ClockConfiguration.class)
	@TestPropertySource(properties = {
		"spring.clock.timezone-id: Europe/London",
		"spring.clock.mock-datetime: 2007-12-03T10:15:30",
	})
	static class TimezoneAndMockDatetime {
		@Autowired
		private Clock clock;
	
		@Test
		void contextLoads() {
			assertEquals(ZoneId.of("Europe/London"), clock.getZone());
			assertEquals("2007-12-03T10:15:30", LocalDateTime.now(clock).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		}
	}
}
