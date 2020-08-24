package com.safetynet.alertsystem;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alertsystem.service.AlertService;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class AlertControllerTests {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	AlertService alertService;
	
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
	@DisplayName("tests: if the controller send to the riht page & sucess status")
	public void testSubmitPersonInfoForm() throws Exception {
		
		mockMvc.perform(post("/addPersonForm")
				.param("firstName", "john")
				.param("lastName", "something")
				.param("address", "so long")
				.param("city", "Why no many")
				.param("zip", "parameters")
				.param("phone", "000000000")
				.param("email", "mybad@gmail.com"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/person"));
	}

}
