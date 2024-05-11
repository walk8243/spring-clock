package xyz.walk8243.springframework.clock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringClockApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(SpringClockApplication.class, args);

		context.getBean(ClockTask.class).execute();
	}

}
