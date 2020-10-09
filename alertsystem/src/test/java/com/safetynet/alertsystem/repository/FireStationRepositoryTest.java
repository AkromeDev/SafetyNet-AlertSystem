package com.safetynet.alertsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.PersonalInformation;

class FireStationRepositoryTest {

	static private FireStationRepository fireRepo;
	static private ArrayList<PersonalInformation> personalList;
	static private ArrayList<Integer> list = new ArrayList<Integer>();
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ModelDAO.fetchPersonalInformationFromJson();
		
		list.add(1);
		list.add(2);
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
	@DisplayName("Tests if the first gotten from the person is the right one and if the list has the right size")
	void getPeopleFromStationTest() {
		personalList = fireRepo.getPeopleFromStation(list);
		
		System.out.println(personalList);
		
		PersonalInformation personalInfo = personalList.get(0);
		
		assertEquals(personalInfo.getAddress(), "1509 Culver St");
		assert(personalList.size() > 5);
	}
	
	@Test
	void findFireStationByNumberTest() {
		
		assertNotNull(fireRepo.findFireStationAreasByNumber(list));
	}

}
