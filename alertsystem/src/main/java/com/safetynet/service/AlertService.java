package com.safetynet.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.servlet.view.RedirectView;

import com.safetynet.repository.AlertRepository;

import Model.PersonalInformation;

public class AlertService {

	AlertRepository alertRepo = new AlertRepository();
	
	public Integer getListSize() {
		return alertRepo.getListSize();
	}
	
	public List<PersonalInformation> getPeopleList() {
		return alertRepo.getPeopleList();
	}
	
	public PersonalInformation findPersonByID(Integer id) {
		return alertRepo.findExistingPerson(id);
	}
	
	public void addOrUpdtePersonInfo(PersonalInformation personInfo) {
		
		PersonalInformation existingPerson = alertRepo.findExistingPerson(personInfo.getId());
		
		if (existingPerson == null) {
			alertRepo.addPerson(personInfo);
			
		} else {
			existingPerson.setFirstName(personInfo.getFirstName());
			existingPerson.setLastName(personInfo.getLastName());
			existingPerson.setAddress(personInfo.getAddress());
			existingPerson.setCity(personInfo.getCity());
			existingPerson.setZip(personInfo.getZip());
			existingPerson.setPhone(personInfo.getPhone());
			existingPerson.setEmail(personInfo.getEmail());
		}
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/person");
	}
	
	public List<PersonalInformation> addDataFromJson() throws ClientProtocolException, IOException {
		
		return alertRepo.addDataFromJson();
	}
}
