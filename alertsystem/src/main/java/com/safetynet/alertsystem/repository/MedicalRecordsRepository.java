package com.safetynet.alertsystem.repository;

import org.springframework.stereotype.Repository;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.MedicalRecords;

@Repository
public class MedicalRecordsRepository {
	
	ModelDAO modelDAO = new ModelDAO();

	public MedicalRecords saveMedicalRecord(MedicalRecords mediRecord) {
		
		mediRecord.setId(findNewId(mediRecord));
		
		return modelDAO.addMedicalRecordToList(mediRecord);
	}

	public MedicalRecords putMedicalRecord(MedicalRecords mediRecord) {

		return modelDAO.findAndUpdateMedicalRecordbyNames(mediRecord);
	}
	
	public MedicalRecords deleteMedicalRecord(String firstName, String lastName) {
		
		return modelDAO.deleteMedicalRecord(firstName, lastName);
	}
	
	public Integer findNewId(MedicalRecords mediRecord) {
		Integer id = 0;
		
		for (MedicalRecords record: modelDAO.getMedicalRecordsFromJson()) {
			if (record.getId() > id) {
				id = record.getId();
			}
		}
	
		return id + 1;
		}
	}
