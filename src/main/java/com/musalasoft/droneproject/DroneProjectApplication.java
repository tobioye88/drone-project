package com.musalasoft.droneproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DroneProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneProjectApplication.class, args);
	}

}
