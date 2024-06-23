package xyz.walk8243.springframework.clock.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

class ClockConfigurationTest {
	private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ");

	@Nested
	@SpringBootTest(classes = ClockConfiguration.class)
	static class NoProperties {
		@Autowired private ZoneId zoneId;
		@Autowired private Clock clock;
	
		@Test
		void contextLoads() {
			assertEquals(ZoneId.systemDefault(), zoneId);
			assertEquals(ZoneId.systemDefault(), clock.getZone());
			assertEquals(ZonedDateTime.now().format(DATETIME_FORMAT), ZonedDateTime.now(clock).format(DATETIME_FORMAT));
		}
	}

	@Nested
	@SpringBootTest(classes = ClockConfiguration.class)
	@TestPropertySource(properties = {
		"spring.clock.timezone-id: Europe/London",
	})
	static class OnlyTimezone {
		@Autowired private ZoneId zoneId;
		@Autowired private Clock clock;
	
		@Test
		void contextLoads() {
			assertEquals(ZoneId.of("Europe/London"), zoneId);
			assertEquals(ZoneId.of("Europe/London"), clock.getZone());
			assertEquals(ZonedDateTime.now(zoneId).format(DATETIME_FORMAT), ZonedDateTime.now(clock).format(DATETIME_FORMAT));
			assertNotEquals(ZonedDateTime.now().format(DATETIME_FORMAT), ZonedDateTime.now(clock).format(DATETIME_FORMAT));
		}
	}

	@Nested
	@SpringBootTest(classes = ClockConfiguration.class)
	@TestPropertySource(properties = {
		"spring.clock.mock-datetime: 2007-12-03T10:15:30",
	})
	static class OnlyMockDatetime {
		@Autowired private ZoneId zoneId;
		@Autowired private Clock clock;
	
		@Test
		void contextLoads() {
			assertEquals(ZoneId.systemDefault(), zoneId);
			assertEquals(ZoneId.systemDefault(), clock.getZone());
			assertEquals("2007-12-03 10:15:30+0900", ZonedDateTime.now(clock).format(DATETIME_FORMAT));
		}
	}

	@Nested
	@SpringBootTest(classes = ClockConfiguration.class)
	@TestPropertySource(properties = {
		"spring.clock.timezone-id: America/Los_Angeles",
		"spring.clock.mock-datetime: 2007-12-03T10:15:30",
	})
	static class TimezoneAndMockDatetime {
		@Autowired private ZoneId zoneId;
		@Autowired private Clock clock;
	
		@Test
		void contextLoads() {
			assertEquals(ZoneId.of("America/Los_Angeles"), zoneId);
			assertEquals(ZoneId.of("America/Los_Angeles"), clock.getZone());
			assertEquals("2007-12-03 10:15:30-0800", ZonedDateTime.now(clock).format(DATETIME_FORMAT));
		}
	}
}
