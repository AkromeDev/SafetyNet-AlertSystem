package com.safetynet.alertsystem.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alertsystem.AlertsystemApplication;
import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.repository.RestRepository;
import com.safetynet.alertsystem.service.RestService;

@WebMvcTest(RestController.class)
@ContextConfiguration(classes=AlertsystemApplication.class)
@AutoConfigureMockMvc
public class RestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean 
	RestService restService;
	
	static private PersonalInformation person = new PersonalInformation();
	static private MedicalRecords record = new MedicalRecords();
	static private FireStations station = new FireStations();
	
	static private String personJson = new String();
	static private JSONObject recordJson = new JSONObject();
	static private JSONObject stationJson = new JSONObject();
	
	private String personString = "{\n" + 
			"\"firstName\":\"Gary\",\n" + 
			"\"lastName\":\"Goodspeed\",\n" + 
			"\"address\":\"some road somewhere in the world\",\n" + 
			"\"city\":\"What else than Annecy\",\n" + 
			"\"zip\":\"606060\",\n" + 
			"\"phone\":\"call the devil\",\n" + 
			"\"email\":\"TheGary@gmail.com\"\n" + 
			"}";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		person = new PersonalInformation("Jojo", "Pierre", "Chantant", "Les Bourgeois", 
				"C'est comme", "les cochons", "vieux & bete", 1);
		
		ArrayList<String> allergies = new ArrayList<String>(Arrays.asList("flowers", "Rexona deodorants"));
		ArrayList<String> medications = new ArrayList<String>(Arrays.asList("Dope", "Marie-Jeanne"));
		
		record = new MedicalRecords("Nelson", "Mandela", "06/06/1006", allergies, medications, 10);
		
		station = new FireStations("Germène Street", 1, 69);
		
		personJson = new JSONObject(person).toString();
		recordJson = new JSONObject(record);
		stationJson = new JSONObject(station);
	}

//	@Test
//	@DisplayName("tests POST /person")
//	public void testPostPerson1() throws Exception {
//		
//		 MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/person")
//			      .accept(MediaType.APPLICATION_JSON_VALUE).content(personJson)).andReturn();
//			   
//			   int status = mvcResult.getResponse().getStatus();
//			   String content = mvcResult.getResponse().getContentAsString();
//	}
	
	@Test
	@DisplayName("tests POST /person")
	public void testPostPerson1() throws Exception {
		
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(personString))
		.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("childrenInHousehold\":[{\"firstName\":\"")))
		.andExpect(content().string(containsString("\"roomiesInHousehold\":")));
	}
	
//	@Test
//	@DisplayName("tests PUT /person")
//	public void testPutPerson1() throws Exception {
//		
//		mockMvc.perform(get("/person"))
//		.andExpect(status().is2xxSuccessful())
//		.andExpect(view().name("person"))
//		.andExpect(model().size(2))
//		.andExpect(model().attributeExists("person"))
//		.andExpect(model().attributeExists("numberToSave"));
//	}
//	
//	@Test
//	@DisplayName("tests DELETE /person")
//	public void testDeletePerson1() throws Exception {
//		
//		mockMvc.perform(get("/person"))
//		.andExpect(status().is2xxSuccessful())
//		.andExpect(view().name("person"))
//		.andExpect(model().size(2))
//		.andExpect(model().attributeExists("person"))
//		.andExpect(model().attributeExists("numberToSave"));
//	}
	
}
