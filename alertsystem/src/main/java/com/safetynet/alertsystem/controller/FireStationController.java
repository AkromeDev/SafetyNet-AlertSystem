package com.safetynet.alertsystem.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.service.FireStationService;

@Controller
public class FireStationController {
	
	private static final Logger logger = LogManager.getLogger("FireStationController");
	
	private List<PersonalInformation> firestations;
	
	private FireStationService fireStationService;
	
	// TODO to change when we create a FireStationService class
	@Autowired
	public FireStationController(FireStationService fireStationService) {
		super();
		this.fireStationService = fireStationService;
	}
	
//	@ResponseBody
//	@RequestMapping(value="/firestation/{station}", method=RequestMethod.GET)
//	public ResponseEntity<JSONArray> getPeopleFromFireStations(@PathVariable("station") String station) {
//		
//		logger.info("HTTP GET request recieved at /firestation/{station} URL");
//		
//		List<PersonalInformation> peopleList = fireStationService.getPeopleFromStation(station);
//		
//		JSONArray jsonPeopleArray = new JSONArray(peopleList);
//		 
//		ResponseEntity<JSONArray> peopleFromFireStation = ResponseEntity.ok(jsonPeopleArray);
//		
//		return peopleFromFireStation;
//	}
	
	@ResponseBody
	@GetMapping(value="/firestation")
	public JSONArray getPeopleFromFireStations2(@RequestParam String station) {
		
		logger.info("HTTP GET request recieved at /firestation/{station} URL");
		
		List<PersonalInformation> peopleList = fireStationService.getPeopleFromStation(station);
		
		JSONArray jsonPeopleArray = new JSONArray(peopleList);
		 
//		ResponseEntity<JSONArray> peopleFromFireStation = ResponseEntity.ok(jsonPeopleArray);
		
		return jsonPeopleArray;
	}
	
}
