package com.safetynet.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.PersonalInformation;

class ModelDAOTest {
	
	static ArrayList<PersonalInformation> personalList;
	static ArrayList<FireStations> fireStationList;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		personalList = ModelDAO.fetchPersonalInformationFromJson();
		fireStationList = ModelDAO.fetchFireStationsFromJson();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	// @TODO: Ask to Nick what is causing the error, the test seems to work when the key is not matching. 
	@DisplayName("PI Tests if the first name of the first array is the right one")
	@Test
	void fetchPersonalInformationTest0() throws ClientProtocolException, IOException {
		PersonalInformation personalInfo = personalList.get(0);
		
		assertEquals(personalInfo.getFirstName(), "John");
	}
	
	@DisplayName("PI Tests if the last name of the first array is the right one")
	@Test
	void fetchPersonalInformationTest1() throws ClientProtocolException, IOException {

		PersonalInformation personalInfo = personalList.get(0);
		
		assertEquals(personalInfo.getLastName(), "Boyd");
	}
	
	@DisplayName("PI Tests id the first adress of the list matches the first address of the Json file")
	@Test
	void fetchPersonalInformationTest2() throws ClientProtocolException, IOException {
		
		PersonalInformation personalInfo = personalList.get(0);
		
		assertEquals(personalInfo.getAddress(), "1509 Culver St");
	}
	
	@DisplayName("PI Tests if the PersonalInformation lits's size is bigger than 5")
	@Test
	void fetchPersonalInformationTest3() throws ClientProtocolException, IOException {
		
		assert(personalList.size() > 5);
	}
	
	@DisplayName("FS Tests if the size of the ArrayList is bigger than 5")
	@Test
	void fetchFireStationsFromJson1() throws ClientProtocolException, IOException {
		
		assert(fireStationList.size() > 5);
	}
	
	@DisplayName("FS Tests id the first adress of the list matches the first address of the Json file")
	@Test
	void fetchFireStationsFromJson2() throws ClientProtocolException, IOException {
		
		FireStations fireStation = fireStationList.get(0);
		
		assertEquals(fireStation.getAddress(), "1509 Culver St");
	}

}
