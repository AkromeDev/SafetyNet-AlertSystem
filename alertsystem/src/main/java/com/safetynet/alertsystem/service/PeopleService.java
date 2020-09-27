package com.safetynet.alertsystem.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alertsystem.model.HabitantAndRecords;
import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.repository.PeopleRepository;
import com.safetynet.alertsystem.util.SummaryUtil;

@Service
public class PeopleService {

	PeopleRepository peopleRepo;
	SummaryUtil util = new SummaryUtil();
	
	@Autowired
	public PeopleService(PeopleRepository peopleRepo) {
		super();
		this.peopleRepo = peopleRepo;
	}

	public ArrayList<HabitantAndRecords> getChildrenFromAddress(String address) {
		
		return peopleRepo.getChildrenFromAddress(address);
	}
	
	public ArrayList<HabitantAndRecords> getRoomiesFromAddress(String address) {
		
		return peopleRepo.getRoomiesFromAddress(address);
	}

	public JSONArray deleteAllButNamesAgeFromJson(JSONArray childrenObject) {
		
		JSONArray uptdatedJsonArray = new JSONArray();
		
        for (int i = 0; i < childrenObject.length(); i++) {
        	
            JSONObject jsonObject = (JSONObject) childrenObject.get(i);
            
            jsonObject.remove("address");
    		jsonObject.remove("city");
    		jsonObject.remove("zip");
    		jsonObject.remove("phone");
    		jsonObject.remove("email");
    		jsonObject.remove("birthdate");
    		jsonObject.remove("medications");
    		jsonObject.remove("allergies");
    		
    		uptdatedJsonArray.put(jsonObject);
        }
	
		return uptdatedJsonArray;
	}

	public ArrayList<HabitantAndRecords> getPersonInfo(String firstName, String lastName) {

		return peopleRepo.getPersonInfo(firstName, lastName);
	}

	public JSONArray deleteCityZipPhoneFromJson(JSONArray personJsonArray) {

		JSONArray uptdatedJsonArray = new JSONArray();
		
        for (int i = 0; i < personJsonArray.length(); i++) {
        	
            JSONObject jsonObject = (JSONObject) personJsonArray.get(i);
            
    		jsonObject.remove("city");
    		jsonObject.remove("zip");
    		jsonObject.remove("phone");
    		jsonObject.remove("birthdate");
    		
    		uptdatedJsonArray.put(jsonObject);
        }
	
		return uptdatedJsonArray;
	}

	public ArrayList<PersonalInformation> getPeopleMails(String city) {

		return peopleRepo.getPeopleMails(city);
	}

	public JSONArray deleteAllButMailFromJson(JSONArray mailsJsonArray) {
		
		JSONArray uptdatedJsonArray = new JSONArray();
		
        for (int i = 0; i < mailsJsonArray.length(); i++) {
        	
            JSONObject jsonObject = (JSONObject) mailsJsonArray.get(i);
            
            
            jsonObject.remove("firstName");
            jsonObject.remove("lastName");
            jsonObject.remove("address");
            jsonObject.remove("city");
    		jsonObject.remove("zip");
    		jsonObject.remove("phone");
    		jsonObject.remove("id");
    		
    		
    		uptdatedJsonArray.put(jsonObject);
        }
	
		return uptdatedJsonArray;
	}
}
