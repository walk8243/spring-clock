package xyz.walk8243.springframework.clock.example;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClockTask {
	private final Clock clock;

	public void execute() {
		log.info("system time: {}", LocalDateTime.now());
		log.info("clock time: {}", LocalDateTime.now(clock));
		log.info("zone: {}", clock.getZone().toString());
	}
}
