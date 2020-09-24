package com.safetynet.alertsystem.repository;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.util.OutputUtil;

@Repository
public class FireStationRepository {
	
	private static final Logger logger = LogManager.getLogger("FireStationRepository");
	
	ModelDAO modelDAO = new ModelDAO();
	OutputUtil util = new OutputUtil();
	// TODO: Ask Nick why I had to instantiate the modelDAO variable in order not to have a NullPointerExceptions
	
	private ArrayList<PersonalInformation> habitantsList = new ArrayList<PersonalInformation>();
	private ArrayList<PersonalInformation> peopleFromStationList = new ArrayList<PersonalInformation>();
	private ArrayList<FireStations> fireStationList = new ArrayList<FireStations>();
	private ArrayList<MedicalRecords> medicalRecords = new ArrayList<MedicalRecords>();

	public ArrayList<PersonalInformation> getPeopleFromStation(Integer station) {
		
		fireStationList = ModelDAO.fetchFireStationsFromJson();
		
		ArrayList<FireStations> fireStations = findFireStationAreasByNumber(station);
		
 		habitantsList = modelDAO.getPeopleFromJson();
		
		peopleFromStationList = findPeopleInFireStationAreaByAddress(fireStations);
		
		return peopleFromStationList;
	}
	
	public ArrayList<FireStations> findFireStationAreasByNumber(Integer station) {
		
		ArrayList<FireStations> chosenFireStation = new ArrayList<FireStations>();
		
		for (FireStations fireStation : fireStationList) {
			if (fireStation.getStation().equals(station)) {
				chosenFireStation.add(fireStation);
			} 
		}
		return chosenFireStation;
		// TODO ask Nick if this kind of method is at it's right place in this class! thanks Nick!
		// TODO Ninick should I test as I create code or should I wait until my code is approximately ready?
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
}
