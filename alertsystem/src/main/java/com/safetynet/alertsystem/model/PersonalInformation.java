package com.safetynet.alertsystem.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonalInformation {
	
	@Size(max= 20, message="If that's really the first name... I am sorry... please make it shorter")
	@NotEmpty(message="We are saving life! we need a first name stupid pumkin")
	private String firstName;
	
	@Size(max= 20, message="If that's really the last name... I am sorry... please make it shorter")
	@NotEmpty(message="We are saving life! we need a last name retard Hill-Billy")
	private String lastName;
	
	@NotEmpty(message="We are saving life! we need an adress dumnut")
	private String address;
	
	@NotEmpty(message="man! fill it up")
	private String city;
	
	@NotEmpty(message="EVERYTHING")
	private String zip;
	
	@NotEmpty(message="IS")
	private String phone;
	
	@NotEmpty(message="MANDATORY")
	@Email
	private String email;
	
	private Integer Id;
	
	public PersonalInformation(String firstName, String lastName, String address, String city, String zip, String phone,
			String email, Integer id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		Id = id;
	}

	public PersonalInformation() {
		// empty constructor for the addPersonInfoForm() method
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

	public void setLastName(String lasttName) {
		this.lastName = lasttName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}


}
