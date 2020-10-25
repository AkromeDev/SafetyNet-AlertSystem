package com.safetynet.alertsystem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alertsystem.AlertsystemApplication;
import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.service.RestService;

@Tag("controllers")
@ComponentScan(basePackages = "com.safetynet.alertsystem") 
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
		
		person = new PersonalInformation("Gary", "Goodspeed", "1509 Culver St", "Culver", 
				"97451", "841-874-6512", "TheGary@gmail.com", 1);
		
		ArrayList<String> allergies = new ArrayList<String>(Arrays.asList("flowers", "Rexona deodorants"));
		ArrayList<String> medications = new ArrayList<String>(Arrays.asList("Dope", "Marie-Jeanne"));
		
		record = new MedicalRecords("Nelson", "Mandela", "06/06/1006", allergies, medications, 10);
		
		station = new FireStations("Germène Street", 1, 1);
		
		ModelDAO.fetchPersonalInformationFromJson();
	}
	
	@Test
	@DisplayName("tests POST /person")
	public void testPostPerson() throws Exception {
		
        when(restService.savePerson(any(PersonalInformation.class))).thenReturn(person);

        mockMvc.perform(post("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Gary"))
                .andExpect(jsonPath("$.lastName").value("Goodspeed"))
                .andExpect(jsonPath("$.address").value("1509 Culver St"))
                .andExpect(jsonPath("$.city").value("Culver"))
                .andExpect(jsonPath("$.zip").value("97451"))
                .andExpect(jsonPath("$.phone").value("841-874-6512"))
                .andExpect(jsonPath("$.email").value("TheGary@gmail.com"));

    }
	
	@Test
	@DisplayName("tests POST /firestation")
	public void testPostFirestation() throws Exception {
		
        when(restService.saveFire(any(FireStations.class))).thenReturn(station);

        mockMvc.perform(post("/firestation")
                .content(new ObjectMapper().writeValueAsString(station))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.address").value("Germène Street"))
                .andExpect(jsonPath("$.station").value("1"));

    }
	
	@Test
	@DisplayName("tests POST /medicalRecord")
	public void testPostMedicalRecord() throws Exception {
		
        when(restService.saveMedicalRecord(any(MedicalRecords.class))).thenReturn(record);

        mockMvc.perform(post("/medicalRecord")
                .content(new ObjectMapper().writeValueAsString(record))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Nelson"))
                .andExpect(jsonPath("$.lastName").value("Mandela"))
                .andExpect(jsonPath("$.birthdate").value("06/06/1006"))
                .andExpect(jsonPath("$.allergies").value(record.getAllergies()))
                .andExpect(jsonPath("$.medications").value(record.getMedications()));

    }
	
	@Test
	@DisplayName("tests PUT /person")
	public void testPutPerson() throws Exception {
		
        when(restService.putPersonalInformation(any(PersonalInformation.class))).thenReturn(person);

        mockMvc.perform(put("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstName").value("Gary"))
                .andExpect(jsonPath("$.lastName").value("Goodspeed"))
                .andExpect(jsonPath("$.address").value("1509 Culver St"))
                .andExpect(jsonPath("$.city").value("Culver"))
                .andExpect(jsonPath("$.zip").value("97451"))
                .andExpect(jsonPath("$.phone").value("841-874-6512"))
                .andExpect(jsonPath("$.email").value("TheGary@gmail.com"));

    }
	
	@Test
	@DisplayName("tests PUT /firestation")
	public void testPutFirestation() throws Exception {
		
        when(restService.putFirestation(any(FireStations.class))).thenReturn(station);

        mockMvc.perform(put("/firestation")
                .content(new ObjectMapper().writeValueAsString(station))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.address").value("Germène Street"))
                .andExpect(jsonPath("$.station").value("1"));

    }
	
	@Test
	@DisplayName("tests PUT /medicalRecord")
	public void testPutMedicalRecord() throws Exception {
		
        when(restService.putMedicalRecord(any(MedicalRecords.class))).thenReturn(record);

        mockMvc.perform(put("/medicalRecord")
                .content(new ObjectMapper().writeValueAsString(record))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstName").value("Nelson"))
                .andExpect(jsonPath("$.lastName").value("Mandela"))
                .andExpect(jsonPath("$.birthdate").value("06/06/1006"))
                .andExpect(jsonPath("$.allergies").value(record.getAllergies()))
                .andExpect(jsonPath("$.medications").value(record.getMedications()));

    }
	
	@Test
	@DisplayName("tests DELETE /person")
	public void testDeletePerson() throws Exception {
		
        when(restService.deletePerson(anyString(), anyString())).thenReturn(person);

        mockMvc.perform(delete("/person?firstName=Gary&lastName=Goodspeed"))
		        .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Gary"))
                .andExpect(jsonPath("$.lastName").value("Goodspeed"))
                .andExpect(jsonPath("$.address").value("1509 Culver St"))
                .andExpect(jsonPath("$.city").value("Culver"))
                .andExpect(jsonPath("$.zip").value("97451"))
                .andExpect(jsonPath("$.phone").value("841-874-6512"))
                .andExpect(jsonPath("$.email").value("TheGary@gmail.com"));

    }
	
	@Test
	@DisplayName("tests DELETE /firestation")
	public void testFirestation() throws Exception {
		
        when(restService.deleteFirestation(anyString(), anyInt())).thenReturn(station);

        mockMvc.perform(delete("/firestation?address=Germène Street&station=1"))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.address").value("Germène Street"))
                .andExpect(jsonPath("$.station").value("1"));
    }
	
	@Test
	@DisplayName("tests DELETE /medicalRecord")
	public void testMedicaRecord() throws Exception {
		
        when(restService.deleteMedicalRecord(anyString(), anyString())).thenReturn(record);

        mockMvc.perform(delete("/medicalRecord?firstName=John&lastName=Boyd"))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.firstName").value("Nelson"))
                .andExpect(jsonPath("$.lastName").value("Mandela"))
                .andExpect(jsonPath("$.birthdate").value("06/06/1006"))
                .andExpect(jsonPath("$.allergies").value(record.getAllergies()))
                .andExpect(jsonPath("$.medications").value(record.getMedications()));
    }

}
