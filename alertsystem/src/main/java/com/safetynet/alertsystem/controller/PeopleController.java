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
import com.safetynet.alertsystem.service.PeopleService;

@Controller
public class PeopleController {
	
	private static final Logger logger = LogManager.getLogger("FireStationController");
	
	private PeopleService peopleService;
	
	@Autowired
	public PeopleController(PeopleService peopleService) {
		super();
		this.peopleService = peopleService;
	}
	
//	@ResponseBody
//	@GetMapping(value="/firestation")
//	public ResponseEntity<String> getPeopleFromFireStations(@RequestParam ArrayList<Integer> stationNumber) {
//		
//		logger.info("HTTP GET request recieved at /firestation?station=X URL");
//		
//		ArrayList<PersonalInformation> peopleList = peopleService.getPeopleFromStation(stationNumber);
//		JSONArray jsonPeopleArray = new JSONArray(peopleList);
//		jsonPeopleArray = peopleService.deleteCityZipEmailFromJson(jsonPeopleArray);
//		
//		JSONObject numberofAdultsAndChildren = peopleService.numberOfAdultsAndChildrenIntoJsonObject(peopleList);
//		
//		numberofAdultsAndChildren.put("peopleNearFireStation",(Object)jsonPeopleArray);
//		
//		return new ResponseEntity<String>(numberofAdultsAndChildren.toString(1), HttpStatus.OK);
//	}
	
	@ResponseBody
	@GetMapping(value="/childAlert")
	public ResponseEntity<String> getStationFromAdress(@RequestParam String address) {
		
		logger.info("HTTP GET request recieved at /childAlert?address=X URL");
		
		ArrayList<HabitantAndRecords> childrenList = peopleService.getChildrenFromAddress(address);
		
		JSONArray childrenObject = new JSONArray(childrenList);
		
		childrenObject = peopleService.deleteAllButNamesAgeFromJson(childrenObject);
		
		ArrayList<HabitantAndRecords> peopleLivingWithChildren = peopleService.getRoomiesFromAddress(address);
		
		JSONObject household = new JSONObject();
		
		household.put("childrenInHousehold", childrenObject);
		household.put("roomiesInHousehold", peopleLivingWithChildren);
		
		return new ResponseEntity<String>(household.toString(), HttpStatus.OK);
	}
	
}
