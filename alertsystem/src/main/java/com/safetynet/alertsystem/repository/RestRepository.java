package com.safetynet.alertsystem.repository;

import org.springframework.stereotype.Repository;

import com.safetynet.alertsystem.dao.ModelDAO;
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
	
	}
