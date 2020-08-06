package com.safetynet.alertsystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsystemApplication {
	
	private static final Logger logger = LogManager.getLogger("App");

	public static void main(String[] args) {
		logger.info("Initializing SafetyNet App");
		SpringApplication.run(AlertsystemApplication.class, args);
	}

}
