package xyz.walk8243.springframework.clock;

import java.time.Clock;
import java.time.ZoneId;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfiguration {

	@Value("${spring.clock.timezone-id}")
	private String timezoneId;

	@Bean
	public ZoneId zoneId() {
		if (Objects.isNull(timezoneId) || timezoneId.isBlank()) {
			return ZoneId.systemDefault();
		}
		return ZoneId.of(timezoneId);
	}

	@Bean
	public Clock clock(final ZoneId zoneId) {
		return Clock.system(zoneId);
	}
}
