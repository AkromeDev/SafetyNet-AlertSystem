package com.safetynet.alertsystem.repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.HabitantAndRecords;
import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.model.PersonalInformation;

@Repository
public class PeopleRepository {
	
	private static final Logger logger = LogManager.getLogger("FireStationRepository");
	
	ModelDAO modelDAO = new ModelDAO();
	
	public ArrayList<HabitantAndRecords> getChildrenFromAddress(String address) {
		
		ArrayList<HabitantAndRecords> childrenList = new ArrayList<HabitantAndRecords>();
		
		for (HabitantAndRecords child: modelDAO.getHabitantsAndRecordList()) {
			if (child.getAge() < 18) {
				if (child.getAddress().equals(address)) {
					childrenList.add(child);
				}
			}
		}
		return childrenList;
	}
	
	public ArrayList<HabitantAndRecords> getRoomiesFromAddress(String address) {
		
		ArrayList<HabitantAndRecords> roomiesList = new ArrayList<HabitantAndRecords>();
		
		for (HabitantAndRecords roomy: modelDAO.getHabitantsAndRecordList()) {
			if (roomy.getAge() >= 18) {
				if (roomy.getAddress().equals(address)) {
					roomiesList.add(roomy);
				}
			}
		}
		return roomiesList;
	}

	public ArrayList<FireStations> getFirestations() {
		
		return modelDAO.getFireStationFromJson();
	}

	public ArrayList<HabitantAndRecords> getPersonInfo(String firstName, String lastName) {
		
		ArrayList<HabitantAndRecords> chosenOnes = new ArrayList<HabitantAndRecords>();
		
		for (HabitantAndRecords potentialOne: modelDAO.getHabitantsAndRecordList()) {
				if (potentialOne.getFirstName().equals(firstName) && potentialOne.getLastName().contentEquals(lastName)) {
					chosenOnes.add(potentialOne);
				}
			}
		return chosenOnes;
		
	}

	public ArrayList<PersonalInformation> getPeopleMails(String city) {
		
		ArrayList<PersonalInformation> people = new ArrayList<PersonalInformation>();
		
		for (PersonalInformation person: modelDAO.getPeopleFromJson()) {
				if (person.getCity().equals(city)) {
					people.add(person);
			}
		}
		return people;
	}
}
