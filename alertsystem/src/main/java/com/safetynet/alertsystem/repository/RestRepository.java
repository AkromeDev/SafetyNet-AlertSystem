package com.safetynet.alertsystem.repository;

import java.util.ArrayList;

import org.json.JSONTokener;
import org.springframework.stereotype.Repository;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.model.PersonalInformation;

@Repository
public class RestRepository {
	
	ModelDAO modelDAO = new ModelDAO();

	public MedicalRecords saveMedicalRecord(MedicalRecords mediRecord) {
		
		mediRecord.setId(findMediRecoNewId());
		
		return modelDAO.addMedicalRecordToList(mediRecord);
	}

	public MedicalRecords putMedicalRecord(MedicalRecords mediRecord) {

		return modelDAO.findAndUpdateMedicalRecordbyNames(mediRecord);
	}
	
	public MedicalRecords deleteMedicalRecord(String firstName, String lastName) {
		
		return modelDAO.deleteMedicalRecord(firstName, lastName);
	}
	
	public Integer findMediRecoNewId() {
		Integer id = 0;
		
		for (MedicalRecords record: modelDAO.getMedicalRecordsFromJson()) {
			if (record.getId() > id) {
				id = record.getId();
			}
		}
	
		return id + 1;
	}
	
	public ArrayList<PersonalInformation> getPeople() {
		
		return ModelDAO.fetchPersonalInformationFromJson();
	}
	
	public PersonalInformation savePerson(PersonalInformation person) {
		
		person.setId(findPersonNewId());
		
		return modelDAO.addPersonToList(person);
	}
	
	public Integer findPersonNewId() {
		Integer id = 0;
		
		for (PersonalInformation human: modelDAO.getPeopleFromJson()) {
			if (human.getId() > id) {
				id = human.getId();
			}
		}
	
		return id + 1;
	}

	public PersonalInformation putPersonalInformation(PersonalInformation person) {
		
		return modelDAO.findAndUpdatePersonalInformation(person);
	}

	public PersonalInformation deletePerson(String firstName, String lastName) {

		return modelDAO.deletePerson(firstName, lastName);
	}

	public FireStations saveFire(FireStations fire) {
		fire.setId(findStationNewId());
		
		return modelDAO.addStationToList(fire);
	}
	
	public Integer findStationNewId() {
		Integer id = 0;
		
		for (FireStations station: modelDAO.getFireStationFromJson()) {
			if (station.getId() > id) {
				id = station.getId();
			}
		}
	
		return id + 1;
	}

	public FireStations putFirestation(FireStations fire) {
		
		return modelDAO.findAndUpdateFirestation(fire);
	}

	public FireStations deleteFirestation(String address, Integer station) {
		
		return modelDAO.deleteFirestation(address, station);
	}

	}
