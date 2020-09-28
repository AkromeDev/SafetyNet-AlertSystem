package com.safetynet.alertsystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alertsystem.controller.AlertController;
import com.safetynet.alertsystem.controller.CustomErrorController;
import com.safetynet.alertsystem.controller.FireStationController;
import com.safetynet.alertsystem.controller.PeopleController;

@SpringBootTest
class AlertsystemApplicationTests {

	@Autowired
	private AlertController alertController;
	
	@Autowired
	private CustomErrorController errorController;
	
	@Autowired
	private FireStationController fireController;
	
	@Autowired
	private PeopleController peopleController;

	@Test
	public void contextLoadsAlert() throws Exception {
		assertThat(alertController).isNotNull();
	}
	
	@Test
	public void contextLoadsError() throws Exception {
		assertThat(errorController).isNotNull();
	}
	
	@Test
	public void contextLoadsFire() throws Exception {
		assertThat(fireController).isNotNull();
	}
	
	@Test
	public void contextLoadsPeople() throws Exception {
		assertThat(peopleController).isNotNull();
	}

}
