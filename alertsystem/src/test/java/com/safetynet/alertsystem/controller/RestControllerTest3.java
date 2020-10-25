package com.safetynet.alertsystem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alertsystem.AlertsystemApplication;
import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.service.RestService;

@Tag("controllers")
@WebMvcTest(value = AlertsystemApplication.class)
public class RestControllerTest3 {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RestService restService;
	
	static private PersonalInformation person = new PersonalInformation();
	static private MedicalRecords record = new MedicalRecords();
	static private FireStations station = new FireStations();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		person = new PersonalInformation("Jojo", "Pierre", "Chantant", "Les Bourgeois", 
				"C'est comme", "les cochons", "vieux & bete", 1);
		
		ArrayList<String> allergies = new ArrayList<String>(Arrays.asList("flowers", "Rexona deodorants"));
		ArrayList<String> medications = new ArrayList<String>(Arrays.asList("Dope", "Marie-Jeanne"));
		
		record = new MedicalRecords("Nelson", "Mandela", "06/06/1006", allergies, medications, 10);
		
		station = new FireStations("Germène Street", 1, 69);
	}
	
	private PersonalInformation newPerson() {
		
        PersonalInformation person = new PersonalInformation();
        
        person.setId(1);
        person.setFirstName("Gary");
        person.setLastName("Goodspeed");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("TheGary@gmail.com");
        
        return person;
    }
	
	@Test
	@DisplayName("tests POST /person")
	public void testPostPerson1() throws Exception {
		
        when(restService.savePerson(any(PersonalInformation.class))).thenReturn(person);

        mockMvc.perform(post("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Gary"))
                .andExpect(jsonPath("$.lastName").value("BoyGoodspeedd"))
                .andExpect(jsonPath("$.address").value("1509 Culver St"))
                .andExpect(jsonPath("$.city").value("Culver"))
                .andExpect(jsonPath("$.zip").value("97451"))
                .andExpect(jsonPath("$.phone").value("841-874-6512"))
                .andExpect(jsonPath("$.email").value("TheGary@gmail.com"));

    }
	
//	@Test
//	@DisplayName("tests POST /firestation")
//	public void testPostFirestation() throws Exception {
//		
//        when(restService.saveFire(any(FireStations.class))).thenReturn(station);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/firestation")
//                .content(new ObjectMapper().writeValueAsString(station))
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("utf-8"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.email").value("TheGary@gmail.com"));
//
//    }

}
