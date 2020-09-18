package com.safetynet.alertsystem.repository;

import java.io.IOException;
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
	List<PersonalInformation> peopleFromStationList;
	List<FireStations> fireStationList;

	public List<PersonalInformation> getPeopleFromStation(int station) throws ClientProtocolException, IOException {
		
		peopleFromStationList = modelDAO.fetchPersonalInformationFromJson();
		fireStationList = modelDAO.fetchFireStationsFromJson();
		
		
		
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
	}
	
	public List<PersonalInformation> getPeopleInFireStationArea(String address) {
		
		peopleFromStationList.clear();
		
		for(PersonalInformation personInArea : habitantsList) {
			if (personInArea.getAddress().equals(address)) {
				peopleFromStationList.add(personInArea);
			}
		}
		return peopleFromStationList;
	}
}
