package xyz.walk8243.springframework.clock.example;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import xyz.walk8243.springframework.clock.ClockConfiguration;

@SpringBootApplication
public class SpringClockExampleApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(SpringClockExampleApplication.class, args);

		context.getBean(ClockTask.class).execute();
	}

}
