package com.safetynet.alertsystem.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.service.FireStationService;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerTest {

	FireStations fireStation;
	FireStationService fireService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		ModelDAO.fetchPersonalInformationFromJson();
	}

	@Test
	@DisplayName("tests /person: if the controller send to the right page/sucess status")
	public void testGetPersonalInfo() throws Exception {
		
		mockMvc.perform(get("/firestation")

				.param("stationNumber", new String[]{"1", "2"}))
		        .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(content().string(containsString("Peter")))
				.andExpect(content().string(containsString("Duncan")))
				.andExpect(content().string(containsString("644 Gershwin Cir")));
	}
	
	@Test
	@DisplayName("tests /flood/stations: if the controller send to the right page/sucess status and all addresses from the 1st and 2nd stations")
	public void testGetHouseholds() throws Exception {
		
		mockMvc.perform(get("/flood/stations?stations=1&stations=2"))
		        .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(content().string(containsString("Peter")))
				.andExpect(content().string(containsString("Duncan")))
				.andExpect(content().string(containsString("644 Gershwin Cir")))
				.andExpect(content().string(containsString("892 Downing Ct")))
				.andExpect(content().string(containsString("908 73rd St")))
				.andExpect(content().string(containsString("947 E. Rose Dr")))
				.andExpect(content().string(containsString("951 LoneTree Rd")));
	}
	
	@Test
	@DisplayName("tests /phoneAlert: if the controller send to the right page/sucess status and all phone numbers from the 1st station")
	public void testGetPhonesFromPeopleInArea() throws Exception {
		
		mockMvc.perform(get("/phoneAlert?firestation=1"))
		        .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(content().string(containsString("841-874-7462")))
				.andExpect(content().string(containsString("841-874-8547")))
				.andExpect(content().string(containsString("841-874-6512")))
				.andExpect(content().string(containsString("841-874-7784")))
				.andExpect(content().string(containsString("841-874-7784")))
				.andExpect(content().string(containsString("841-874-7784")));
	}
	
	@Test
	@DisplayName("tests /fire: if the controller send to the right page/sucess status and all phone numbers from the 1st station")
	public void testGetStationFromAdress() throws Exception {
		
		mockMvc.perform(get("/fire?address=1509 Culver St"))
		        .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(content().string(containsString("John")))
				.andExpect(content().string(containsString("Boyd")))
				.andExpect(content().string(containsString("841-874-6512")))
				.andExpect(content().string(containsString("\"responsibleStation\": \"3\"")));
	}
	

}
