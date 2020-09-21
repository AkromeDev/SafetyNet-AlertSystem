package com.safetynet.alertsystem.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.safetynet.alertsystem.constants.URIDataConstants;
import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.model.PersonalInformation;

public class ModelDAO {
	
	private static final Logger logger = LogManager.getLogger("ModelDAO");
	
	
	ArrayList<PersonalInformation> peopleFromJson = new ArrayList<PersonalInformation>();
	ArrayList<FireStations> fireStationFromJson = new ArrayList<FireStations>();

	/**
	 * @return a populated ArrayList of PersonalInformation
	 * @throws ClientProtocolException if we have problem with network call (connection problem)
	 * @throws IOException if we have problem with network call (connection problem)
	 */
	
	public static ArrayList<PersonalInformation> fetchPersonalInformationFromJson() throws ClientProtocolException, IOException {
		
		ArrayList<PersonalInformation> personalInformation = new ArrayList<PersonalInformation>();
		
		JSONObject json = new JSONObject(NetworkDAO.request(URIDataConstants.LINK_JASON_DATA));
		// TODO: use property file to use the code
		
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
	
	public static ArrayList<FireStations> fetchFireStationsFromJson() {
		
		ArrayList<FireStations> fireStationsList = new ArrayList<FireStations>();
		
		JSONObject json = new JSONObject();
		
		try {
			json = new JSONObject(NetworkDAO.request(URIDataConstants.LINK_JASON_DATA));
		} catch (JSONException e) {
			logger.error("JSONException while fetching the fire stations from json link", e);
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException while fetching the fire stations from json link", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IOException while fetching the fire stations from json link", e);
			e.printStackTrace();
		}
		
		JSONArray jsonFireStation = json.getJSONArray("firestations");
		
		for(int i = 0; i < jsonFireStation.length(); i++) {
			JSONObject jsonObject = jsonFireStation.getJSONObject(i);
			
			FireStations fireStation = new FireStations();
			
			String address = jsonObject.getString("address");
			int station = jsonObject.getInt("station");
			
			// populate our fire station model class with the information above
			fireStation.setAddress(address);
			fireStation.setStation(station);
			fireStation.setId(i);
			// TODO try it with i + 1! who knows!
			
			fireStationsList.add(fireStation);
			
		}
		
		return fireStationsList;
	}

	public static ArrayList<MedicalRecords> fetchMedicalRecords() throws ClientProtocolException, IOException {
		
		ArrayList<MedicalRecords> medicalRecordsList = new ArrayList<MedicalRecords>();
		
		JSONObject json = new JSONObject(NetworkDAO.request(URIDataConstants.LINK_JASON_DATA));
		
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
	
	public ArrayList<PersonalInformation> getPeopleFromJson() {
		return peopleFromJson;
	}

	public void setPeopleFromJson(ArrayList<PersonalInformation> peopleFromJson) {
		this.peopleFromJson = peopleFromJson;
	}
	
	public void addPersonToList(PersonalInformation person) {
		peopleFromJson.add(person);
	}

	public ArrayList<FireStations> getFireStationFromJson() {
		return fireStationFromJson;
	}

	public void setFireStationFromJson(ArrayList<FireStations> fireStationFromJson) {
		this.fireStationFromJson = fireStationFromJson;
	}
	
	public void addStationToList(FireStations station) {
		fireStationFromJson.add(station);
	}
}
