package com.safetynet.alertsystem.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.repository.AlertRepository;


@Service
public class AlertService {

	AlertRepository alertRepo;
	
	@Autowired
	public AlertService(AlertRepository alertRepo) {
		super();
		this.alertRepo = alertRepo;
	}

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
