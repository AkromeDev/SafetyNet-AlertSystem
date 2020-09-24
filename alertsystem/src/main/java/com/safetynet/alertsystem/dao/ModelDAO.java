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
	
	private static JSONObject json = new JSONObject();
	
	private static ArrayList<PersonalInformation> peopleFromJson = new ArrayList<PersonalInformation>();
	private static ArrayList<FireStations> fireStationFromJson = new ArrayList<FireStations>();
	private static ArrayList<MedicalRecords> medicalRecordsFromJson = new ArrayList<MedicalRecords>();

	/**
	 * @return a populated ArrayList of PersonalInformation
	 * @throws ClientProtocolException if we have problem with network call (connection problem)
	 * @throws IOException if we have problem with network call (connection problem)
	 */
	
	public static void loadDataFromJson() {
		
		try {
			json = new JSONObject(NetworkDAO.request(URIDataConstants.LINK_JASON_DATA));
		} catch (JSONException e) {
			logger.error("JSONException while loading the data from json link", e);
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException while loading the data from json link", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IOException while loading the data from json link", e);
			e.printStackTrace();
		}
		
		// TODO: use property file for the data link
		// TODO Ask Nick if it makes sense to pass the Json Data From a string back to a Json object (NetworkDAO method)
		// TODO Ask Nick if this method is ok, should I use a return value => local Variables or instance variable, when should I use them?
		
	}
	
	public static void addAllDataToLists() {
		
			fetchFireStationsFromJson();
		
			fetchMedicalRecordsFromJson();
	}
	
	public static ArrayList<PersonalInformation> fetchPersonalInformationFromJson() {
		
		loadDataFromJson();
		
		addAllDataToLists();
		
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
			
			peopleFromJson.add(personInfo);
		}
		
		return peopleFromJson;
	}
	
	public static ArrayList<FireStations> fetchFireStationsFromJson() {
		
		loadDataFromJson();
		
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
			
			fireStationFromJson.add(fireStation);
			
		}
		
		return fireStationFromJson;
	}

	public static ArrayList<MedicalRecords> fetchMedicalRecordsFromJson() {
		
		loadDataFromJson();
		
		JSONArray jsonMedicalRecords = json.getJSONArray("medicalrecords");
		
		for(int i = 0; i < jsonMedicalRecords.length(); i++) {
			JSONObject jsonMedicalRecord = jsonMedicalRecords.getJSONObject(i);
			
			MedicalRecords medicalRecord = new MedicalRecords();
			
			String firstName = jsonMedicalRecord.getString("firstName");
			String lastName = jsonMedicalRecord.getString("lastName");
			String birthdate = jsonMedicalRecord.getString("birthdate");
			
			// since "medications" and "allergies are JSONArrays we have to pass them into a ArrayLists
			JSONArray jsonMedications = jsonMedicalRecord.getJSONArray("medications");
			ArrayList<String> medicationList = new ArrayList<String>();
			
			for(int j = 0; j < jsonMedications.length(); j++) {
				String medication = jsonMedications.getString(j);
				medicationList.add(medication);
			}
			
			JSONArray jsonAllergies = jsonMedicalRecord.getJSONArray("allergies");
			ArrayList<String> allergiesList = new ArrayList<String>();
			
			for(int k = 0; k < jsonAllergies.length(); k++) {
				String allergy = jsonAllergies.getString(k);
				allergiesList.add(allergy);
			}
			
			// populate our medical record model class with the information above
			medicalRecord.setFirstName(firstName);
			medicalRecord.setLasttName(lastName);
			medicalRecord.setBirthdate(birthdate);
			medicalRecord.setMedications(medicationList);
			medicalRecord.setAllergies(allergiesList);
			medicalRecord.setId(i);
			
			medicalRecordsFromJson.add(medicalRecord);
			
		}
		
		return medicalRecordsFromJson;
	}
	
	public ArrayList<PersonalInformation> getPeopleFromJson() {
		return peopleFromJson;
	}

	public void setPeopleFromJson(ArrayList<PersonalInformation> peopleFromJson) {
		ModelDAO.peopleFromJson = peopleFromJson;
	}
	
	public void addPersonToList(PersonalInformation person) {
		peopleFromJson.add(person);
	}

	public ArrayList<FireStations> getFireStationFromJson() {
		return fireStationFromJson;
	}

	public void setFireStationFromJson(ArrayList<FireStations> fireStationFromJson) {
		ModelDAO.fireStationFromJson = fireStationFromJson;
	}
	
	public void addStationToList(FireStations station) {
		fireStationFromJson.add(station);
	}

	public ArrayList<MedicalRecords> getMedicalRecordsFromJson() {
		return medicalRecordsFromJson;
	}

	public void setMedicalRecordsFromJson(ArrayList<MedicalRecords> medicalRecordsFromJson) {
		ModelDAO.medicalRecordsFromJson = medicalRecordsFromJson;
	}
	
	public void addMedicalRecordList(MedicalRecords record) {
		medicalRecordsFromJson.add(record);
	}
	
}
