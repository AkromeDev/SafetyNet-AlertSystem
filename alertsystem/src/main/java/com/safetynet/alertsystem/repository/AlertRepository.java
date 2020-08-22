package com.safetynet.alertsystem.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Repository;

import com.safetynet.alertsystem.constants.URIDataConstants;
import com.safetynet.alertsystem.dao.ModelDAO;
import com.safetynet.alertsystem.dao.NetworkDAO;
import com.safetynet.alertsystem.model.PersonalInformation;

@Repository
public class AlertRepository {
	
	private List<PersonalInformation> people = new ArrayList<PersonalInformation>();
	private static Integer id = 1;
	
	public List<PersonalInformation> getPeopleList() {
		return people;
	}
	
	public void addPerson(PersonalInformation personalInfo) {
		personalInfo.setId(id++);
		people.add(personalInfo);
	}
	
	public Integer getListSize() {
		return people.size();
	}
	
	public PersonalInformation findExistingPerson(Integer index) {
		for(PersonalInformation person: people) {
			if(person.getId().equals(index)) {
				return person;
			}
		}
		return null;
	}
	
	public Integer setId() {
		return id++;
	}
	
	public List<PersonalInformation> addDataFromJson() throws ClientProtocolException, IOException {
		ModelDAO modelDao = new ModelDAO();
		
		if (people.size() < 1 ) {
		// TODO: use property file to use the code
		people = modelDao.fetchPersonalInformation(NetworkDAO.request(URIDataConstants.LINK_JASON_DATA));
		}
		
		return people;
	}

}
