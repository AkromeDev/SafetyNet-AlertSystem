package com.safetynet.alertsystem.service;

import java.util.ArrayList;

import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.repository.RestRepository;

@Service
public class RestService {

	RestRepository restRepo;
	
	@Autowired
	public RestService(RestRepository restRepo) {
		super();
		this.restRepo = restRepo;
	}

	public MedicalRecords saveMedicalRecord(MedicalRecords mediRecord) {

		return restRepo.saveMedicalRecord(mediRecord);
	}

	public MedicalRecords putMedicalRecord(MedicalRecords mediRecord) {
		
		return restRepo.putMedicalRecord(mediRecord);
	}

	public MedicalRecords deleteMedicalRecord(String firstName, String lastName) {
	
		return restRepo.deleteMedicalRecord(firstName, lastName);
	}
	
	public ArrayList<PersonalInformation> getPeople() {
		
		return restRepo.getPeople();
	}
	
	public PersonalInformation savePerson(PersonalInformation person) {

		return restRepo.savePerson(person);
	}
	
	public PersonalInformation putPersonalInformation(PersonalInformation person) {
		
		return restRepo.putPersonalInformation(person);
	}

	public PersonalInformation deletePerson(String firstName, String lastName) {

		return restRepo.deletePerson(firstName, lastName);
	}

	public FireStations saveFire(FireStations fire) {
		
		return restRepo.saveFire(fire);
	}

	public FireStations putFirestation(FireStations fire) {
		
		return restRepo.putFirestation(fire);
	}

	public FireStations deleteFirestation(String address, Integer station) {
		
		return restRepo.deleteFirestation(address, station);
	}

}