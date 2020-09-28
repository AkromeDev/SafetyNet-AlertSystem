package com.safetynet.alertsystem.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

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
public class FireStationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		ModelDAO.fetchPersonalInformationFromJson();
	}

	@Test
	@DisplayName("tests /person: if the controller send to the riht page/sucess status/Model size/view name")
	public void testGetPersonalInfo() throws Exception {
		
		ArrayList<Integer> station = new ArrayList<Integer>();
		station.add(1);
		
		mockMvc.perform(get("/firestation")
				.param("stationNumber[0].id", "1"))
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().string(containsString("John")));
		
//	    mockMvc.perform(get("/person")
//	            .sessionAttr("userClientObject", this.userClientObject)
//	            .param("firstName", firstName)
//	            .param("lastName", lastName)               
//	    ).andDo(print())
//	     .andExpect(status().isOk())
//	            .andExpect(content().contentType("application/json"))
//	            .andExpect(jsonPath("$[0].id").exists())
//	            .andExpect(jsonPath("$[0].fn").value("Marge"));
	}
	

}
