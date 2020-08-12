package com.safetynet.alertsystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class AlertControllerTests {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testShowPersonInfoForm() throws Exception {
		
		mockMvc.perform(get("/addPersonForm"))
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("addPersonForm"))
		.andExpect(model().size(1))
		.andExpect(model().attributeExists("personInfo"));
	}
	
	@Test
	public void testSubmitPersonInfoForm() throws Exception {
		
		mockMvc.perform(post("/addPersonForm"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/person"));
	}

}
