package com.safetynet.alertsystem.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.PersonalInformation;

@Repository
public class FireStationRepository {
	
	private static final Logger logger = LogManager.getLogger("FireStationRepository");
	
	ModelDAO modelDAO;
	
	List<PersonalInformation> habitantsList;
	ArrayList<PersonalInformation> peopleFromStationList;
	ArrayList<FireStations> fireStationList;

	public ArrayList<PersonalInformation> getPeopleFromStation(String station) {
		
		fireStationList = modelDAO.fetchFireStationsFromJson();
		
		FireStations fireStation = findFireStationByNumber(station);
		
		peopleFromStationList = findPeopleInFireStationAreaByAddress(fireStation.getAddress());
		
		return peopleFromStationList;
	}
	
	public FireStations findFireStationByNumber(String station) {
		
		for (FireStations fireStation : fireStationList) {
			if (fireStation.getStation().equals(station)) {
				return fireStation;
			} else {
				logger.error("The fire station number does not exist please try another number.");
				throw new IllegalArgumentException("Station number is not available.");
			}
		}
		
		return null;
		// TODO ask Nick if this kind of method is at it's right place in this class! thanks Nick!
		// TODO Ninick should I test as I create code or should I wait until my code is approximately ready?
	}
	
	public ArrayList<PersonalInformation> findPeopleInFireStationAreaByAddress(String address) {
		
		peopleFromStationList.clear();
		
		for(PersonalInformation personInArea : habitantsList) {
			if (personInArea.getAddress().equals(address)) {
				peopleFromStationList.add(personInArea);
			}
		}
		return peopleFromStationList;
	}
}
