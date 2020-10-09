package com.safetynet.alertsystem.repository;

import org.springframework.stereotype.Repository;

import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.model.MedicalRecords;

@Repository
public class MedicalRecordsRepository {
	
	ModelDAO modelDAO = new ModelDAO();

	public MedicalRecords saveMedicalRecord(MedicalRecords mediRecord) {
		
		return modelDAO.addMedicalRecordList(mediRecord);
	}

	public MedicalRecords putMedicalRecord(MedicalRecords mediRecord) {
		// TODO Auto-generated method stub
		return null;
	}
}
