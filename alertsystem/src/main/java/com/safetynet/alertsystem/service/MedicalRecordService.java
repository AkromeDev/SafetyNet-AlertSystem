package com.safetynet.alertsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordService {

	MedicalRecordsRepository mediRepo;
	
	@Autowired
	public MedicalRecordService(MedicalRecordsRepository mediRepo) {
		super();
		this.mediRepo = mediRepo;
	}

	public MedicalRecords saveMedicalRecord(MedicalRecords mediRecord) {

		return mediRepo.saveMedicalRecord(mediRecord);
	}

	public MedicalRecords putMedicalRecord(MedicalRecords mediRecord) {
		
		return mediRepo.putMedicalRecord(mediRecord);
	}

	public MedicalRecords deleteMedicalRecord(String firstName, String lastName) {
	
		return mediRepo.deleteMedicalRecord(firstName, lastName);
	}
}