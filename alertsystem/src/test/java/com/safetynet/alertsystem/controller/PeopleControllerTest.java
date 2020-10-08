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

@SpringBootTest
@AutoConfigureMockMvc
public class PeopleControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		ModelDAO.fetchPersonalInformationFromJson();
	}

	@Test
	@DisplayName("tests /childAlert: if the controller send to the right page/sucess status/if the data is clustered in ChildrenInHousehold and roomiesInHousehold arrays")
	public void testGetStationFromAdress() throws Exception {
		
		mockMvc.perform(get("/childAlert?address=1509 Culver St"))
		        .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(content().string(containsString("childrenInHousehold\":[{\"firstName\":\"")))
				.andExpect(content().string(containsString("\"roomiesInHousehold\":")));
	}
	
	@Test
	@DisplayName("tests /personInfo: if the controller send to the right page/sucess status/the right person was fetched.")
	public void testPersonInfo() throws Exception {
		
		mockMvc.perform(get("/personInfo?firstName=Zach&lastName=Zemicks"))
		        .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(content().string(containsString("{\"allergies\":[]")))
				.andExpect(content().string(containsString("\"firstName\":\"Zach\"")))
				.andExpect(content().string(containsString("\"lastName\":\"Zemicks\"")))
				.andExpect(content().string(containsString("\"address\":\"892 Downing Ct\"")));
	}
	
	@Test
	@DisplayName("tests /communityEmail: if the controller send to the right page/sucess status/the right person was fetched.")
	public void testGetEmailsFromCity() throws Exception {
		
		mockMvc.perform(get("/communityEmail?city=Culver"))
		        .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(content().string(containsString("jaboyd@email.com")))
				.andExpect(content().string(containsString("drk@email.com")))
				.andExpect(content().string(containsString("tenz@email.com")))
				.andExpect(content().string(containsString("drk@email.com")));
	}
}
