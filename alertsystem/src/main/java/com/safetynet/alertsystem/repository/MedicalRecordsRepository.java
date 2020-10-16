package com.safetynet.alertsystem.repository;

import org.springframework.stereotype.Repository;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.MedicalRecords;

@Repository
public class MedicalRecordsRepository {
	
	ModelDAO modelDAO = new ModelDAO();
	MedicalRecordsRepository medRecRepo = new MedicalRecordsRepository();

	public MedicalRecords saveMedicalRecord(MedicalRecords mediRecord) {
		
		return modelDAO.addMedicalRecordList(mediRecord);
	}

	public MedicalRecords putMedicalRecord(MedicalRecords mediRecord) {

		return medRecRepo.findAndUpdateMedicalRecordbyNames(mediRecord);
	}
	
	public MedicalRecords findAndUpdateMedicalRecordbyNames(MedicalRecords mediRecord) {
		
		MedicalRecords chosenOne = new MedicalRecords();
		
		for (MedicalRecords potentialOne: modelDAO.getMedicalRecordsFromJson()) {
				if (potentialOne.getFirstName().equals(mediRecord.getFirstName()) && potentialOne.getLastName().contentEquals(mediRecord.getLastName())) {
					potentialOne.setAllergies(mediRecord.getAllergies());
					potentialOne.setMedications(mediRecord.getMedications());
					potentialOne.setBirthdate(mediRecord.getBirthdate());
				}
			}
		return chosenOne;
	}

	public MedicalRecords deleteMedicalRecord(String firstName, String lastName) {
		
		return modelDAO.deleteMedicalRecord(firstName, lastName);
	}
}
