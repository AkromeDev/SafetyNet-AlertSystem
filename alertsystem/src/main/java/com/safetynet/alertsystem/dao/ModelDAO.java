package com.safetynet.alertsystem.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.model.PersonalInformation;

public class ModelDAO {
	
	/**
	 * @return a populated ArrayList of PersonalInformation
	 * @throws ClientProtocolException if we have problem with network call (connection problem)
	 * @throws IOException if we have problem with network call (connection problem)
	 */
	
	public ArrayList<PersonalInformation> fetchPersonalInformationFromJson(String rawJson) throws ClientProtocolException, IOException {
		
		ArrayList<PersonalInformation> personalInformation = new ArrayList<PersonalInformation>();
		
		JSONObject json = new JSONObject(rawJson);
		
		JSONArray persons = json.getJSONArray("persons");
		
		for(int i = 0; i < persons.length(); i++) {
			JSONObject jsonPerson = persons.getJSONObject(i);
			
			PersonalInformation personInfo = new PersonalInformation();
			
			String firstName = jsonPerson.getString("firstName");
			String lastName = jsonPerson.getString("lastName");
			String address = jsonPerson.getString("address");
			String city = jsonPerson.getString("city");
			String zip = jsonPerson.getString("zip");
			String phone = jsonPerson.getString("phone");
			String email = jsonPerson.getString("email");
			
			// populate our model class with the information above
			personInfo.setFirstName(firstName);
			personInfo.setLastName(lastName);
			personInfo.setAddress(address);
			personInfo.setCity(city);
			personInfo.setZip(zip);
			personInfo.setPhone(phone);
			personInfo.setEmail(email);
			personInfo.setId(i);
			// TODO the setId implementation has been changed (we used a id = i + 1 formula that did not really make sense) check that it did not create some new bug
			
			personalInformation.add(personInfo);
		}
		
		return personalInformation;
	}
	
public ArrayList<FireStations> fetchFireStationsFromJson(String rawJson) throws ClientProtocolException, IOException {
		
		ArrayList<FireStations> fireStations = new ArrayList<FireStations>();
		
		JSONObject json = new JSONObject(rawJson);
		// TODO replace rawJson with the code from the alertRepository
		
		JSONArray jsonFireStation = json.getJSONArray("firestations");
		
		for(int i = 0; i < jsonFireStation.length(); i++) {
			JSONObject jsonPerson = jsonFireStation.getJSONObject(i);
			
			FireStations fireStation = new FireStations();
			
			String address = jsonPerson.getString("address");
			String station = jsonPerson.getString("station");
			
			// populate our fire station model class with the information above
			fireStation.setAddress(address);
			fireStation.setStation(station);
			fireStation.setId(i);
			
			fireStations.add(fireStation);
			
		}
		
		return fireStations;
	}

	public ArrayList<MedicalRecords> fetchMedicalRecords(String rawJson) throws ClientProtocolException, IOException {
		
		ArrayList<MedicalRecords> medicalRecordsList = new ArrayList<MedicalRecords>();
		
		JSONObject json = new JSONObject(rawJson);
		
		JSONArray jsonMedicalRecords = json.getJSONArray("firestations");
		
		for(int i = 0; i < jsonMedicalRecords.length(); i++) {
			JSONObject jsonMedicalRecord = jsonMedicalRecords.getJSONObject(i);
			
			// TODO you know what to do
			MedicalRecords medicalRecord = new MedicalRecords(null, null, null, null, null, i);
			
			String firstName = jsonMedicalRecord.getString("firstName");
			String lastName = jsonMedicalRecord.getString("lastName");
			String birthdate = jsonMedicalRecord.getString("birthdate");
			String medications = jsonMedicalRecord.getString("medications");
			String allergies = jsonMedicalRecord.getString("allergies");
			
			// populate our medical record model class with the information above
			medicalRecord.setFirstName(firstName);
			medicalRecord.setLasttName(lastName);
			medicalRecord.setBirthdate(birthdate);
			medicalRecord.setMedications(medications);
			medicalRecord.setAllergies(allergies);
			medicalRecord.setId(i);
			
			medicalRecordsList.add(medicalRecord);
			
		}
		
		return medicalRecordsList;
	}
}
