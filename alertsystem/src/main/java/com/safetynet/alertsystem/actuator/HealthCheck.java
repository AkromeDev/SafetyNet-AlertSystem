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

	@Override
	public Health health() {
		
			if (alertService.getPeopleList().isEmpty()) {
				return Health.down().withDetail("Cause", "The data could not be fetched from the provided Link in URIDatContants.java").build();
			}
			
		return Health.up().build();
	}

}
