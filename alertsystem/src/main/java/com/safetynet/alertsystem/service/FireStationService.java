package com.safetynet.alertsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.repository.FireStationRepository;

@Service
public class FireStationService {

	
	FireStationRepository fireStationRepo;
	
	@Autowired
	public FireStationService(FireStationRepository fireStationRepo) {
		super();
		this.fireStationRepo = fireStationRepo;
	}

	public List<PersonalInformation> getPeopleFromStation(String station) {
		
		return fireStationRepo.getPeopleFromStation(station);
	}
}
