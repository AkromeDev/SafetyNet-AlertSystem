package com.safetynet.alertsystem.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	private FireStationService fireStationService;
	
	// TODO to change when we create a FireStationService class
	@Autowired
	public FireStationController(FireStationService fireStationService) {
		super();
		this.fireStationService = fireStationService;
	}
	
	@ResponseBody
	@GetMapping(value="/firestation")
	public ResponseEntity<String> getPeopleFromFireStations(@RequestParam Integer stationNumber) {
		
		logger.info("HTTP GET request recieved at /firestation?station=X URL");
		
		ArrayList<PersonalInformation> peopleList = fireStationService.getPeopleFromStation(stationNumber);
		JSONArray jsonPeopleArray = new JSONArray(peopleList);
		jsonPeopleArray = fireStationService.deleteCityZipEmailFromJson(jsonPeopleArray);
		
		JSONObject numberofAdultsAndChildren = fireStationService.numberOfAdultsAndChildrenIntoJsonObject(peopleList);
		
		numberofAdultsAndChildren.put("peopleNearFireStation",(Object)jsonPeopleArray);
		
		return new ResponseEntity<String>(numberofAdultsAndChildren.toString(), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value="/phoneAlert")
	public ResponseEntity<String> getPhonesFromPeopleInArea(@RequestParam Integer firestation) {
		
		logger.info("HTTP GET request recieved at /phoneAlert?station=X URL");
		
		ArrayList<PersonalInformation> peopleList = fireStationService.getPeopleFromStation(firestation);
		
		JSONArray jsonPeopleArray = new JSONArray(peopleList);
		
		jsonPeopleArray = fireStationService.deleteAllButPhoneFromJson(jsonPeopleArray);
		
		return new ResponseEntity<String>(jsonPeopleArray.toString(), HttpStatus.OK);
	}
	
}
