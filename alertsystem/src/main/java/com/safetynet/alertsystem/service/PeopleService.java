package com.safetynet.alertsystem.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alertsystem.model.HabitantAndRecords;
import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.repository.PeopleRepository;
import com.safetynet.alertsystem.util.OutputUtil;

@Service
public class PeopleService {

	PeopleRepository peopleRepo;
	OutputUtil util = new OutputUtil();
	
	@Autowired
	public PeopleService(PeopleRepository peopleRepo) {
		super();
		this.peopleRepo = peopleRepo;
	}

	public ArrayList<PersonalInformation> getPeopleFromStation(ArrayList<Integer> station) {
		
		return peopleRepo.getPeopleFromStation(station);
	}
	
	public JSONArray deleteCityZipEmailFromJson(JSONArray jsonArray) {
		
		JSONArray uptdatedJsonArray = new JSONArray();
		
        for (int i = 0; i < jsonArray.length(); i++) {
        	
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            
    		jsonObject.remove("city");
    		jsonObject.remove("zip");
    		jsonObject.remove("email");
    		jsonObject.remove("id");
    		
    		uptdatedJsonArray.put(jsonObject);
        }
        
		return uptdatedJsonArray;
	}
	
	public JSONArray deleteAllButPhoneFromJson(JSONArray jsonArray) {
		
		JSONArray uptdatedJsonArray = new JSONArray();
		
        for (int i = 0; i < jsonArray.length(); i++) {
        	
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            
            jsonObject.remove("firstName");
            jsonObject.remove("lastName");
            jsonObject.remove("address");
    		jsonObject.remove("city");
    		jsonObject.remove("zip");
    		jsonObject.remove("email");
    		jsonObject.remove("id");
    		
    		uptdatedJsonArray.put(jsonObject);
        }
	
		return uptdatedJsonArray;
	}

	public JSONObject numberOfAdultsAndChildrenIntoJsonObject(ArrayList<PersonalInformation> peopleList) {
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		data.put("numberOfChildren", util.findNumberOfChildren(peopleRepo.matchPeopleToMedicalRecord(peopleList)).toString());
		data.put("numberOfAdults", util.findNumberOfAdults(peopleRepo.matchPeopleToMedicalRecord(peopleList)).toString());
		
		return new JSONObject(data);
	}

	public HashMap<String, ArrayList<HabitantAndRecords>> createHouseholds(ArrayList<PersonalInformation> peopleList) {
		
		return peopleRepo.mergeWithMedicalRecords(peopleList);
	}

	public ArrayList<HabitantAndRecords> getChildrenFromAddress(String address) {
		
		return peopleRepo.getChildrenFromAddress(address);
	}
	
	public ArrayList<HabitantAndRecords> getRoomiesFromAddress(String address) {
		
		return peopleRepo.getRoomiesFromAddress(address);
	}

	public JSONArray deleteAddressCityZipEmailBirthdateFromJson(JSONArray jsonArray) {

		JSONArray uptdatedJsonArray = new JSONArray();
		
        for (int i = 0; i < jsonArray.length(); i++) {
        	
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            
            jsonObject.remove("address");
    		jsonObject.remove("city");
    		jsonObject.remove("zip");
    		jsonObject.remove("email");
    		jsonObject.remove("birthdate");
    		
    		uptdatedJsonArray.put(jsonObject);
        }
	
		return uptdatedJsonArray;
	}

	public JSONObject findStationFromAddress(String address) {
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		data.put("responsibleStation", util.findStationFromAddress(address));
		
		return new JSONObject(data);
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
}
