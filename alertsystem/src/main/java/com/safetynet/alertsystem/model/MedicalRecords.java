package com.safetynet.alertsystem.model;

public class MedicalRecords {
	
	private String firstName;
	private String lasttName;
	private String birthdate;
	private String medications;
	private int id;
	
	public MedicalRecords(String firstName, String lasttName, String birthdate, String medications, int id) {
		super();
		this.firstName = firstName;
		this.lasttName = lasttName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasttName() {
		return lasttName;
	}

	public void setLasttName(String lasttName) {
		this.lasttName = lasttName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getMedications() {
		return medications;
	}

	public void setMedications(String medications) {
		this.medications = medications;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
