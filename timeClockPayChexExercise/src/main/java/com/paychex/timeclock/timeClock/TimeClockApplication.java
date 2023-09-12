package com.paychex.timeclock.timeClock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.paychex.*")
public class TimeClockApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeClockApplication.class, args);
	}

}
