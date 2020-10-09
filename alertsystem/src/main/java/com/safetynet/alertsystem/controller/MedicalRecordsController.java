package com.safetynet.alertsystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.service.MedicalRecordService;

@Controller
public class MedicalRecordsController {
	
	private static final Logger logger = LogManager.getLogger("AlertController");
	
	private MedicalRecordService mediService;
	
	@Autowired
	public MedicalRecordsController(MedicalRecordService mediService) {
		super();
		this.mediService = mediService;
	}
	
	@PostMapping(value="/POST/medicalRecord")
	public MedicalRecords postMedicalRecord(@RequestParam MedicalRecords mediRecord) {
		logger.info("HTTP POST request recieved at /POST/medicalRecord URL");		
		
		return mediService.saveMedicalRecord(mediRecord);
	}
	
	@PutMapping("/PUT/medicalRecord")
	public MedicalRecords submitPersonInfoForm(@RequestParam MedicalRecords mediRecord) {
		
		logger.info("HTTP PUT request recieved at /PUT/medicalRecord URL");
		
		
		return mediService.putMedicalRecord(mediRecord);
	}
	
	@DeleteMapping("/DELETE/medicalRecord")
	public ModelAndView deleteMedicalRecord(@RequestParam String firstName, String lastName) {
		
		logger.info("HTTP GET request recieved at /person URL");
		
		String viewName = "person";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("person", alertService.addDataFromJson());
		model.put("numberToSave", alertService.getListSize());
		
		return new ModelAndView(viewName, model);
	}
}
