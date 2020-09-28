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
	
	@ResponseBody
	@GetMapping(value="/personInfo")
	public ResponseEntity<String> personInfo(@RequestParam String firstName, String lastName) {
		
		logger.info("HTTP GET request recieved at /personInfo?firstName=X&lastName=Y URL");
		
		ArrayList<HabitantAndRecords> personInfo = peopleService.getPersonInfo(firstName, lastName);
		
		JSONArray personJsonArray = new JSONArray(personInfo);
		
		personJsonArray = peopleService.deleteCityZipPhoneFromJson(personJsonArray);
		
		return new ResponseEntity<String>(personJsonArray.toString(), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value="/communityEmail")
	public ResponseEntity<String> getEmailsFromCity(@RequestParam String city) {
		
		logger.info("HTTP GET request recieved at /personInfo?firstName=X&lastName=Y URL");
		
		ArrayList<PersonalInformation> peopleMails = peopleService.getPeopleMails(city);
		
		JSONArray mailsJsonArray = new JSONArray(peopleMails);
		
		mailsJsonArray = peopleService.deleteAllButMailFromJson(mailsJsonArray);
		
		return new ResponseEntity<String>(mailsJsonArray.toString(), HttpStatus.OK);
	}
}
