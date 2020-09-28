package com.safetynet.alertsystem.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.safetynet.alertsystem.model.FireStations;
import com.safetynet.alertsystem.model.HabitantAndRecords;
import com.safetynet.alertsystem.model.MedicalRecords;
import com.safetynet.alertsystem.model.PersonalInformation;

public class ModelDAO {
	
	private static final Logger logger = LogManager.getLogger("ModelDAO");
	
	private static JSONObject json = new JSONObject();
	
	private static ArrayList<PersonalInformation> peopleFromJson = new ArrayList<PersonalInformation>();
	private static ArrayList<FireStations> fireStationFromJson = new ArrayList<FireStations>();
	private static ArrayList<MedicalRecords> medicalRecordsFromJson = new ArrayList<MedicalRecords>();
	private static ArrayList<HabitantAndRecords> habitantsAndRecordList = new ArrayList<HabitantAndRecords>();

	/**
	 * @return a populated ArrayList of PersonalInformation
	 * @throws ClientProtocolException if we have problem with network call (connection problem)
	 * @throws IOException if we have problem with network call (connection problem)
	 */
	
	public static void loadDataFromJson() {
		
		try {
			// load Properties file
	    	Properties props = new Properties();
	    	props.load(new FileInputStream("src/main/resources/config.properties"));
	    	
	    	// read the properties File
	    	String password = props.getProperty("alert.url");
	    	
			json = new JSONObject(NetworkDAO.request(password));
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
		
		// TODO use property file for the data link
	}
	
	public static void addAllDataToLists() {
		
			fetchFireStationsFromJson();
		
			fetchMedicalRecordsFromJson();
			
			mergeWithMedicalRecords(peopleFromJson);
	}
	
	public static ArrayList<PersonalInformation> fetchPersonalInformationFromJson() {
		
		loadDataFromJson();
		
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
			
			peopleFromJson.add(personInfo);
		}
		
		addAllDataToLists();
		
		return peopleFromJson;
	}
	
	public static ArrayList<HabitantAndRecords> mergeWithMedicalRecords(ArrayList<PersonalInformation> peopleList) {
		
		for(PersonalInformation person : peopleList) {
			for(MedicalRecords record : medicalRecordsFromJson) {
				if (person.getFirstName().equals(record.getFirstName()) & person.getLastName().equals(record.getLasttName())) {
					HabitantAndRecords habAndRe = new HabitantAndRecords();
					
					habAndRe.setFirstName(person.getFirstName());
					habAndRe.setLastName(person.getLastName());
					habAndRe.setAddress(person.getAddress());
					habAndRe.setCity(person.getCity());
					habAndRe.setZip(person.getZip());
					habAndRe.setPhone(person.getPhone());
					habAndRe.setEmail(person.getEmail());
					habAndRe.setBirthdate(record.getBirthdate());
					habAndRe.calculateAge(record.getBirthdate());
					habAndRe.setMedications(record.getMedications());
					habAndRe.setAllergies(record.getAllergies());
					
					habitantsAndRecordList.add(habAndRe);
				}
			}
		}
		
		return habitantsAndRecordList;
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

	public ArrayList<HabitantAndRecords> getHabitantsAndRecordList() {
		return habitantsAndRecordList;
	}

	public void setHabitantsAndRecordList(ArrayList<HabitantAndRecords> habitantsAndRecordList) {
		ModelDAO.habitantsAndRecordList = habitantsAndRecordList;
	}
	
}
