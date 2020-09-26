package com.safetynet.alertsystem.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alertsystem.model.HabitantAndRecords;
import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.repository.FireStationRepository;
import com.safetynet.alertsystem.util.OutputUtil;

@Service
public class FireStationService {

	FireStationRepository fireStationRepo;
	OutputUtil util = new OutputUtil();
	
	@Autowired
	public FireStationService(FireStationRepository fireStationRepo) {
		super();
		this.fireStationRepo = fireStationRepo;
	}

	public ArrayList<PersonalInformation> getPeopleFromStation(ArrayList<Integer> station) {
		
		return fireStationRepo.getPeopleFromStation(station);
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
		
		data.put("numberOfChildren", util.findNumberOfChildren(fireStationRepo.matchPeopleToMedicalRecord(peopleList)).toString());
		data.put("numberOfAdults", util.findNumberOfAdults(fireStationRepo.matchPeopleToMedicalRecord(peopleList)).toString());
		
		return new JSONObject(data);
	}

	public HashMap<String, ArrayList<HabitantAndRecords>> createHouseholds(ArrayList<PersonalInformation> peopleList) {
		
		return fireStationRepo.mergeWithMedicalRecords(peopleList);
	}

	public ArrayList<HabitantAndRecords> getPeopleFromAddress(String address) {
		
		return fireStationRepo.getPeopleFromAddress(address);
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
	
//	public JSONObject deleteAdCiZiEmBiId(JSONObject householdsObject) {
//		 TODO delete if not useed in the presentation of the project
//		
//		JSONObject updatedObject = new JSONObject();
//		
//		for (int i = 0; i < ((List<PersonalInformation>) householdsObject).size(); i++) {
//			
//			
//        	householdsObject.remove("address");
//        	householdsObject.remove("city");
//        	householdsObject.remove("zip");
//        	householdsObject.remove("email");
//        	householdsObject.remove("birthdate");
//        	householdsObject.remove("id");
//		}
//	
//		return householdsObject;
//	}
}
