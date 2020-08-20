package com.safetynet.service;

import java.util.List;

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
}
