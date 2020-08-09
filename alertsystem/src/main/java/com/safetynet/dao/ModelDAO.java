package com.safetynet.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.safetynet.constants.URIDataConstants;

import Model.PersonalInformation;

public class ModelDAO {
	
	public ArrayList<PersonalInformation> fetchPersonalInformation(String searchFilter) throws ClientProtocolException, IOException {
		
		ArrayList<PersonalInformation> personalInformation = new ArrayList<PersonalInformation>();
		
		String rawJson = NetworkDAO.request(URIDataConstants.LINK_JASON_DATA);
		
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
			personInfo.setLasttName(lastName);
			personInfo.setAdress(address);
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
