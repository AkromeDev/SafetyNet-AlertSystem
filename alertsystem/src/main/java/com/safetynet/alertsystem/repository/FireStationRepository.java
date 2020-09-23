package com.safetynet.alertsystem.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.PersonalInformation;

@Repository
public class FireStationRepository {
	
	private static final Logger logger = LogManager.getLogger("FireStationRepository");
	
	ModelDAO modelDAO = new ModelDAO();
	// TODO: Ask Nick why I had to instantiate the modelDAO variable in order not to have a NullPointerExceptions
	
	ArrayList<PersonalInformation> habitantsList = new ArrayList<PersonalInformation>();
	ArrayList<PersonalInformation> peopleFromStationList = new ArrayList<PersonalInformation>();
	ArrayList<FireStations> fireStationList = new ArrayList<FireStations>();

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
}
