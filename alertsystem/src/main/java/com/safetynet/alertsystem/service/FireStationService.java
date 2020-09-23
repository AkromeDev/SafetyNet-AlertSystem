package com.safetynet.alertsystem.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.repository.FireStationRepository;

@Service
public class FireStationService {

	
	FireStationRepository fireStationRepo;
	
	@Autowired
	public FireStationService(FireStationRepository fireStationRepo) {
		super();
		this.fireStationRepo = fireStationRepo;
	}

	public ArrayList<PersonalInformation> getPeopleFromStation(Integer station) {
		
		return fireStationRepo.getPeopleFromStation(station);
	}
	
	public JSONArray deleteCityZipEmailFromJson(JSONArray jsonArray) {
		
		// TODO ask Nick: Does this method belongs to a utility class, or is it good tp have it into your service class?
		
		JSONArray uptdatedJsonArray = new JSONArray();
		
        for (int i = 0; i < jsonArray.length(); i++) {
        	
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            
    		jsonObject.remove("city");
    		jsonObject.remove("zip");
    		jsonObject.remove("email");
    		
    		uptdatedJsonArray.put(jsonObject);
        }
	
		return uptdatedJsonArray;
	}
}
