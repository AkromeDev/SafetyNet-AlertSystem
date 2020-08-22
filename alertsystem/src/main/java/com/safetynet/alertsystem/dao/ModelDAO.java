package com.safetynet.alertsystem.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.safetynet.alertsystem.model.PersonalInformation;

public class ModelDAO {
	
	/**
	 * @return a populated ArrayList of PersonalInformation
	 * @throws ClientProtocolException if we have problem with network call (connection problem)
	 * @throws IOException if we have problem with network call (connection problem)
	 */
	
	public ArrayList<PersonalInformation> fetchPersonalInformation(String rawJson) throws ClientProtocolException, IOException {
		
		ArrayList<PersonalInformation> personalInformation = new ArrayList<PersonalInformation>();
		
		JSONObject json = new JSONObject(rawJson);
		
		JSONArray persons = json.getJSONArray("persons");
		
		for(int i = 0; i < persons.length(); i++) {
			JSONObject jsonPerson = persons.getJSONObject(i);
			
			// TODO: Ask Nick if there is a way not to call the constructor in order not to have this list of null
			PersonalInformation personInfo = new PersonalInformation(null, null, null, null, null, null, null, i);
			
			String firstName = jsonPerson.getString("firstName");
			String lastName = jsonPerson.getString("lastName");
			String address = jsonPerson.getString("address");
			String city = jsonPerson.getString("city");
			String zip = jsonPerson.getString("zip");
			String phone = jsonPerson.getString("phone");
			String email = jsonPerson.getString("email");
			int id = i + 1;
			
			
			// populate our model class with the information above
			personInfo.setFirstName(firstName);
			personInfo.setLastName(lastName);
			personInfo.setAddress(address);
			personInfo.setCity(city);
			personInfo.setZip(zip);
			personInfo.setPhone(phone);
			personInfo.setEmail(email);
			personInfo.setId(id);
			
			personalInformation.add(personInfo);
			
		}
		
		return personalInformation;
	}

}
