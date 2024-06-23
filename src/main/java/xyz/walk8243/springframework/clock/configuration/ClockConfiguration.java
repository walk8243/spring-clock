package xyz.walk8243.springframework.clock.configuration;

import java.time.Clock;
import java.time.LocalDateTime;
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
public class ClockConfiguration {

	@Value("${spring.clock.timezone-id:}")
	private String timezoneId;

	@Value("${spring.clock.mock-datetime:}")
	private LocalDateTime mockDatetime;

	@PostConstruct
	public void init() {
		log.debug("Clock Configuration. timezoneId: {}, mockDatetime: {}", timezoneId, mockDatetime);
	}

	@Bean
	@ConditionalOnMissingBean(ZoneId.class)
	public ZoneId zoneId() {
		if (Objects.isNull(timezoneId) || timezoneId.isBlank()) {
			return ZoneId.systemDefault();
		}

		if (ZoneId.SHORT_IDS.containsKey(timezoneId)) {
			return ZoneId.of(timezoneId, ZoneId.SHORT_IDS);
		}

		return ZoneId.of(timezoneId);
	}

	@Bean
	@ConditionalOnMissingBean(Clock.class)
	public Clock clock(final ZoneId zoneId) {
		if (Objects.nonNull(mockDatetime)) {
			return Clock.fixed(mockDatetime.atZone(zoneId).toInstant(), zoneId);
		}
		return Clock.system(zoneId);
	}
}
