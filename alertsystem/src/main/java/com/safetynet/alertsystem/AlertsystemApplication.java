package com.safetynet.alertsystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsystemApplication {

	private static final Logger logger = LogManager.getLogger("App");
	
	public static void main(String[] args) {
		logger.info("Initializing SafetyNet app");
		SpringApplication.run(AlertsystemApplication.class, args);
	}
	// TODO ask Nick how I could get rid of the "spring-boot-logger-log4j2" in my Git Staging. It's really annoying to have to commit it every time
}
