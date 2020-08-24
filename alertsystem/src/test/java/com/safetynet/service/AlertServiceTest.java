package com.safetynet.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.repository.AlertRepository;
import com.safetynet.alertsystem.service.AlertService;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest {

	
	@Mock
	private AlertRepository alerRepositoryMock;
	
	
	@InjectMocks
	private AlertService alertServiceMock;
	
	@Test
	void testGetPeopleList() {
		//Arrange
		PersonalInformation person1 = new PersonalInformation("John", "Doe", "myAss", "something", "no inspiration", "hmm hmm", "so long", 1);
		PersonalInformation person2 = new PersonalInformation("Johna", "Doea", "myAssa", "somethinga", "no inspirationa", "hmma hmma", "so longa", 2);
		
		List<PersonalInformation> mockPersons = Arrays.asList(person1, person2);
		
		Mockito.when(alerRepositoryMock.getPeopleList()).thenReturn(mockPersons);
		
		//Act
		List<PersonalInformation> result = alertServiceMock.getPeopleList();
		
		//Assert
		assertTrue(result.size() == 2);
	}

}
