package com.safetynet.alertsystem.model;

import java.util.ArrayList;

public class MedicalRecords {
	
	private String firstName;
	private String lastName;
	private String birthdate;
	private ArrayList<String> medications;
	private ArrayList<String> allergies;
	private int id;
	
	public MedicalRecords(String firstName, String lastName, String birthdate, ArrayList<String> medications, ArrayList<String> allergies, int id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
		this.id = id;
	}
	
	public MedicalRecords() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLasttName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public ArrayList<String> getMedications() {
		return medications;
	}

	public void setMedications(ArrayList<String> medications) {
		this.medications = medications;
	}
	
	public ArrayList<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(ArrayList<String> allergiesList) {
		this.allergies = allergiesList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
