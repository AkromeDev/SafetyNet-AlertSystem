package com.safetynet.alertsystem.service;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alertsystem.repository.FireStationRepository;

@Service
public class FireStationService {

	
	FireStationRepository fireStationRepo;
	
	@Autowired
	public FireStationService(FireStationRepository fireStationRepo) {
		super();
		this.fireStationRepo = fireStationRepo;
	}

	public JSONArray getPeopleFromStation(int station) {
		
		return fireStationRepo.getPeopleFromStation(station);
	}
}
