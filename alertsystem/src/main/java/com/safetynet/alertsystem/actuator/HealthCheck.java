package com.safetynet.alertsystem.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.safetynet.alertsystem.service.AlertService;

@Component
public class HealthCheck implements HealthIndicator {

	@Autowired
	private AlertService alertService;

	// TODO: Ask Nick why the server is not working https://openclassrooms.com/en/courses/5684146-create-web-applications-efficiently-with-the-spring-boot-mvc-framework/6170301-get-started-with-spring-boot-actuator#/id/r-6170348
	@Override
	public Health health() {
		
			if (alertService.getPeopleList().isEmpty()) {
				return Health.down().withDetail("Cause", "The data could not be fetched from the provided Link in URIDatContants.java").build();
			}
			
		return Health.up().build();
	}

}
