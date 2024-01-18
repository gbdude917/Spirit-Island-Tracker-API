package com.sitracker.sigametracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.sitracker.sigametracker.entity")
public class SiGameTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiGameTrackerApplication.class, args);
	}

}
