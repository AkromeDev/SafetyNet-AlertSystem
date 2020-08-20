package com.safetynet.repository;

import java.util.ArrayList;
import java.util.List;

import Model.PersonalInformation;

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
			// TODO: Question to Nick: Should I always use Integer instead of int, Integer seems to has built in methods.
			if(person.getId().equals(id)) {
				return person;
			}
		}
		return null;
	}

}
