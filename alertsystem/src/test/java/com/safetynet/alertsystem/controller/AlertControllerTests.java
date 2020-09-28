package com.safetynet.alertsystem.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AlertControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("tests: if we have connection and if the homepage is correctly loaded")
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Welcome to the Alert System application from SafetyNet")));
	}
	
	
	@Test
	@DisplayName("TODO")
	public void testGetPersonalInfo() throws Exception {
		
		mockMvc.perform(get("/person"))
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("person"))
		.andExpect(model().size(2))
		.andExpect(model().attributeExists("person"))
		.andExpect(model().attributeExists("numberToSave"
				));
	}
	
	@Test
	@DisplayName("tests: if the controller send to the riht page/sucess status/Model size/view name")
	public void testShowPersonInfoForm() throws Exception {
		
		mockMvc.perform(get("/addPersonForm"))
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("addPersonForm"))
		.andExpect(model().size(1))
		.andExpect(model().attributeExists("personInfo"));
	}
	
	@Test
	@DisplayName("tests: if the controller send to the right page & sucess status")
	public void testSubmitPersonInfoForm() throws Exception {
		
		mockMvc.perform(post("/addPersonForm")
				.param("firstName", "john")
				.param("lastName", "something")
				.param("address", "so long")
				.param("city", "Why no many")
				.param("zip", "parameters")
				.param("phone", "000000000")
				.param("email", "mybad@gmail.com"))
		.andExpect(status().is3xxRedirection());
//		.andExpect(forwardedUrl("/person"));
	}

}
