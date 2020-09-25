package com.safetynet.alertsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alertsystem.model.PersonalInformation;

@ExtendWith(MockitoExtension.class)
class FireStationServiceTest {
	
	private static ArrayList<PersonalInformation> personalList;
	private static FireStationService fireService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		fireService = new FireStationService(null);
		personalList = new ArrayList<PersonalInformation>();
	}

//	@Test
//	void test() {
//		personalList = fireService.getPeopleFromStation(1);
//		
//		PersonalInformation personalInfo = personalList.get(0);
//		
//		assertEquals(personalInfo.getAddress(), "1509 Culver St");
//	}

}
