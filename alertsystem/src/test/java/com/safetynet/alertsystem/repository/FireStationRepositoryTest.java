package com.safetynet.alertsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetynet.alertsystem.model.PersonalInformation;

class FireStationRepositoryTest {

	static private FireStationRepository fireRepo;
	static private ArrayList<PersonalInformation> personalList;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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

	@Test
	void getPeopleFromStationTest() {
		personalList = fireRepo.getPeopleFromStation(1);
		
		PersonalInformation personalInfo = personalList.get(0);
		
		assertEquals(personalInfo.getAddress(), "1509 Culver St");
	}
	
	@Test
	void findFireStationByNumberTest() {
		
		assertNotNull(fireRepo.findFireStationByNumber(1));
	}

}
