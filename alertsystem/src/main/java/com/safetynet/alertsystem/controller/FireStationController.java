package com.safetynet.alertsystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

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

import com.safetynet.alertsystem.model.HabitantAndRecords;
import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.service.FireStationService;

@Controller
public class FireStationController {
	
	private static final Logger logger = LogManager.getLogger("FireStationController");
	
	private FireStationService fireStationService;
	
	@Autowired
	public FireStationController(FireStationService fireStationService) {
		super();
		this.fireStationService = fireStationService;
	}
	
	@ResponseBody
	@GetMapping(value="/firestation")
	public ResponseEntity<String> getPeopleFromFireStations(@RequestParam ArrayList<String> stationNumber) {
		
		logger.info("HTTP GET request recieved at /firestation?station=X URL");
		
		ArrayList<PersonalInformation> peopleList = fireStationService.getPeopleFromStation(stationNumber);
		JSONArray jsonPeopleArray = new JSONArray(peopleList);
		jsonPeopleArray = fireStationService.deleteCityZipEmailFromJson(jsonPeopleArray);
		
		JSONObject numberofAdultsAndChildren = fireStationService.numberOfAdultsAndChildrenIntoJsonObject(peopleList);
		
		numberofAdultsAndChildren.put("peopleNearFireStation",(Object)jsonPeopleArray);
		
		return new ResponseEntity<String>(numberofAdultsAndChildren.toString(1), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value="/phoneAlert")
	public ResponseEntity<String> getPhonesFromPeopleInArea(@RequestParam ArrayList<String> firestation) {
		
		logger.info("HTTP GET request recieved at /phoneAlert?firestation=X URL");
		
		ArrayList<PersonalInformation> peopleList = fireStationService.getPeopleFromStation(firestation);
		
		JSONArray jsonPeopleArray = new JSONArray(peopleList);
		
		jsonPeopleArray = fireStationService.deleteAllButPhoneFromJson(jsonPeopleArray);
		
		return new ResponseEntity<String>(jsonPeopleArray.toString(1), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value="/flood/stations")
	public ResponseEntity<String> getHouseholds(@RequestParam ArrayList<Integer> stations) {
		
		logger.info("HTTP GET request recieved at /stations?stations=X URL");
		
		ArrayList<HabitantAndRecords> peopleList = fireStationService.getHabitantsAndRecordsFromStation(stations);
		
		HashMap<String, ArrayList<HabitantAndRecords>> householdsMap = fireStationService.createHouseholds(peopleList);
		
		JSONObject householdJsonObject = new JSONObject(householdsMap);
		
		return new ResponseEntity<String>(householdJsonObject.toString(), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value="/fire")
	public ResponseEntity<String> getStationFromAdress(@RequestParam String address) {
		
		ArrayList<HabitantAndRecords> peopleList = fireStationService.getPeopleFromAddress(address);
		JSONArray jsonPeopleArray = new JSONArray(peopleList);
		jsonPeopleArray = fireStationService.deleteAddressCityZipEmailBirthdateFromJson(jsonPeopleArray);
		
		JSONObject reponsibleStationAndPeople = fireStationService.findStationFromAddress(address);
		
		reponsibleStationAndPeople.put("peopleNearFireStation",(Object)jsonPeopleArray);
		
		return new ResponseEntity<String>(reponsibleStationAndPeople.toString(1), HttpStatus.OK);
	}
	
}
