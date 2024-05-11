package xyz.walk8243.springframework.clock;

import java.time.Clock;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfiguration {

	@Value("${spring.clock.timezone-id}")
	private String timezoneId;

	@Bean
	public Clock init() {
		final ZoneId zoneId = ZoneId.of(timezoneId);
		return Clock.system(zoneId);
	}
}
