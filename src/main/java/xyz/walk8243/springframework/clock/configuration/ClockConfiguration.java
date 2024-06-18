package xyz.walk8243.springframework.clock.configuration;

import java.time.Clock;
import java.time.ZoneId;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@Slf4j
@Configuration
@ConditionalOnMissingBean(Clock.class)
public class ClockConfiguration {

	@Value("${spring.clock.timezone-id:}")
	private String timezoneId;

	@PostConstruct
	public void init() {
		log.debug("ClockConfiguration initialized. timezoneId: {}", timezoneId);
	}

	@Bean
	@ConditionalOnMissingBean(ZoneId.class)
	public ZoneId zoneId() {
		if (Objects.isNull(timezoneId) || timezoneId.isBlank()) {
			return ZoneId.systemDefault();
		}
		return ZoneId.of(timezoneId);
	}

	@Bean
	@ConditionalOnMissingBean(Clock.class)
	public Clock clock(final ZoneId zoneId) {
		return Clock.system(zoneId);
	}
}
