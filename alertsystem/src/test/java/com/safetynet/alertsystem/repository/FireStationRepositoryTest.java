package com.safetynet.alertsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.PersonalInformation;

class FireStationRepositoryTest {

	ModelDAO modelDAO = new ModelDAO();
	FireStationRepository fireRepo = new FireStationRepository();
	static private ArrayList<Integer> list = new ArrayList<Integer>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		ModelDAO.fetchPersonalInformationFromJson();
		
		list.add(1);
		list.add(2);
	}

	@Test
	@DisplayName("Tests if list from getPeopleFromStation() has the right size")
	void getPeopleFromStationTest() {
		
		ArrayList<PersonalInformation> personList = fireRepo.getPeopleFromStation(list);
		
		assert(personList.size() > 5);
	}
	
	@Test
	@DisplayName("Tests if the first person from the list from getPeopleFromStation() has the right address")
	void getPeopleFromStationTest1() {
		
		ArrayList<PersonalInformation> personList = fireRepo.getPeopleFromStation(list);
		PersonalInformation person = personList.get(0);

		assertEquals(person.getAddress(), "644 Gershwin Cir");
	}
	
	@Test
	@DisplayName("Tests if findFireStationAreasByNumber() returned list isn't null")
	void findFireStationByNumberTest() {
		
		assertNotNull(fireRepo.findFireStationAreasByNumber(list));
	}
	
	@Test
	@DisplayName("Tests if getHabitantsAndRecordsFromStation() returned list isn't null")
	void getHabitantsAndRecordsFromStationTest() {
		
		assertNotNull(fireRepo.getHabitantsAndRecordsFromStation(list));
		
	}

}
