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
	private ModelDAO modelDAO = new ModelDAO();
	private static Integer id = 1;
	
	public List<PersonalInformation> getPeopleList() {
		return modelDAO.getPeopleFromJson();
	}
	
	public void addPerson(PersonalInformation personalInfo) {
		personalInfo.setId(modelDAO.getFireStationFromJson().size() + 1);
		modelDAO.addPersonToList(personalInfo);
	}
	
	public Integer getListSize() {
		return modelDAO.getPeopleFromJson().size();
	}
	
	public PersonalInformation findExistingPerson(Integer index) {
		for(PersonalInformation person: modelDAO.getPeopleFromJson()) {
			if(person.getId().equals(index)) {
				return person;
			}
		}
		return null;
	}
	
	public Integer setId() {
		// TODO this is not used and does not make so much sense, check it up and delete it if needed
		// don't forget to delete the global variable index too! up there.
		
		return id++;
	}
	
	public ArrayList<PersonalInformation> addDataFromJson() throws ClientProtocolException, IOException {
		
		if (modelDAO.getPeopleFromJson().size() < 1 ) {
		people = ModelDAO.fetchPersonalInformationFromJson();
		}
		// TODO I did some modifications here, it might not make sense
		
		return modelDAO.getPeopleFromJson();
	}

}
