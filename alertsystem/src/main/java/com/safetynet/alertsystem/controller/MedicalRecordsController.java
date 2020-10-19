package com.safetynet.alertsystem.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.service.MedicalRecordService;

@RestController
public class MedicalRecordsController {
	
	private static final Logger logger = LogManager.getLogger("AlertController");
	
	private MedicalRecordService mediService;
	
	@Autowired
	public MedicalRecordsController(MedicalRecordService mediService) {
		super();
		this.mediService = mediService;
	}
	
	@PostMapping("/medicalRecord")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> postMedicalRecord(@RequestBody MedicalRecords mediRecord) {
		logger.info("HTTP POST request recieved at /POST/medicalRecord URL");		
		
		JSONObject postMedicalRecord = new JSONObject(mediService.saveMedicalRecord(mediRecord));
		
		return new ResponseEntity<String>(postMedicalRecord.toString(), HttpStatus.OK);
	}
	 
	@PutMapping("/medicalRecord")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<String> putPersonInfoForm(@RequestBody MedicalRecords mediRecord) {
		logger.info("HTTP PUT request recieved at /PUT/medicalRecord URL");
		
		JSONObject putMedicalRecord = new JSONObject(mediService.putMedicalRecord(mediRecord));
		
		return new ResponseEntity<String>(putMedicalRecord.toString(), HttpStatus.OK);
	}
	
	@DeleteMapping("/medicalRecord")
	public ResponseEntity<String> deleteMedicalRecord(@RequestParam String firstName, String lastName) {
		
		logger.info("HTTP PUT request recieved at /DELETE/medicalRecord URL");
		
		JSONObject deleteMedicalRecord = new JSONObject(mediService.deleteMedicalRecord(firstName, lastName));
		
		return new ResponseEntity<String>(deleteMedicalRecord.toString(), HttpStatus.OK);
	}
}
