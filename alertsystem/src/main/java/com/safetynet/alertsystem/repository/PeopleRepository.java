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
	
	private ArrayList<PersonalInformation> habitantsList = new ArrayList<PersonalInformation>();
	private ArrayList<PersonalInformation> peopleFromStationList = new ArrayList<PersonalInformation>();
	private ArrayList<FireStations> fireStationList = new ArrayList<FireStations>();
	private ArrayList<MedicalRecords> medicalRecords = new ArrayList<MedicalRecords>();

	public ArrayList<PersonalInformation> getPeopleFromStation(ArrayList<Integer> station) {
		
		fireStationList = ModelDAO.fetchFireStationsFromJson();
		
		ArrayList<FireStations> fireStations = findFireStationAreasByNumber(station);
		
 		habitantsList = modelDAO.getPeopleFromJson();
		
		peopleFromStationList = findPeopleInFireStationAreaByAddress(fireStations);
		
		return peopleFromStationList;
	}
	
	public ArrayList<FireStations> findFireStationAreasByNumber(ArrayList<Integer> stations) {
		
		ArrayList<FireStations> chosenFireStation = new ArrayList<FireStations>();
		
		for (Integer station: stations) {
			for (FireStations fireStation : fireStationList) {
				if (fireStation.getStation().equals(station)) {
					chosenFireStation.add(fireStation);
				} 
			}
		}
		return chosenFireStation;
	}
	
	public ArrayList<PersonalInformation> findPeopleInFireStationAreaByAddress(ArrayList<FireStations> fireStations) {
		
		peopleFromStationList.clear();
			
		for(FireStations fireSta: fireStations) {
			String address = fireSta.getAddress();
			for(PersonalInformation personInArea : habitantsList) {
				if (personInArea.getAddress().equals(address)) {
					peopleFromStationList.add(personInArea);
				}
			}
		}
		
		return peopleFromStationList;
	}
	
	public ArrayList<MedicalRecords> matchPeopleToMedicalRecord(ArrayList<PersonalInformation> listOfPeople) {
		
		ArrayList<MedicalRecords> machtedRecords = new ArrayList<MedicalRecords>();
		
		for(PersonalInformation person: listOfPeople) {
			
			medicalRecords = modelDAO.getMedicalRecordsFromJson();
			
			for(MedicalRecords record : medicalRecords) {
				if (person.getFirstName().equals(record.getFirstName()) & person.getLastName().equals(record.getLasttName())) {
					machtedRecords.add(record);
				}
			}
		}
		return machtedRecords;
	}

	public HashMap<String, ArrayList<HabitantAndRecords>> mergeWithMedicalRecords(ArrayList<PersonalInformation> peopleList) {
		
		ArrayList<HabitantAndRecords> habitantsAndRecordList = ModelDAO.mergeWithMedicalRecords(peopleList);
		
		HashMap<String, ArrayList<HabitantAndRecords>> householdsMap = new HashMap<String, ArrayList<HabitantAndRecords>>();
		
			for (HabitantAndRecords har: habitantsAndRecordList) {
				if (!householdsMap.containsKey(har.getAddress())) {
					
					ArrayList<HabitantAndRecords> newKeyList = new ArrayList<HabitantAndRecords>();
					newKeyList.add(har);
					
					householdsMap.put(har.getAddress(), newKeyList);
					
				} else {
					householdsMap.get(har.getAddress()).add(har);
				}
			}
		return householdsMap;
	}

	public ArrayList<HabitantAndRecords> getPeopleFromAddress(String address) {
		
		ArrayList<HabitantAndRecords> peopleList = new ArrayList<HabitantAndRecords>();
		
		for (HabitantAndRecords person: modelDAO.getHabitantsAndRecordList()) {
				if (person.getAddress().equals(address)) {
					peopleList.add(person);
			}
		}
		return peopleList;
	}
	
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
}
