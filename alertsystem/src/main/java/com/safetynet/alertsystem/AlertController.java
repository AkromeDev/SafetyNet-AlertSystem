package com.safetynet.alertsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.safetynet.constants.URIDataConstants;
import com.safetynet.dao.ModelDAO;
import com.safetynet.dao.NetworkDAO;

import Model.PersonalInformation;

@Controller
public class AlertController {
	
	private List<PersonalInformation> person = new ArrayList<PersonalInformation>();
	private List<PersonalInformation> medicalRecords;
	private List<PersonalInformation> firestations;
	
	@GetMapping("/person")
	public ModelAndView getPersonalInfo() throws ClientProtocolException, IOException {
		
		ModelDAO modelDao = new ModelDAO();
		
		if (person.size() < 1 ) {
			// TODO: ask Nick where the code that chooses the data source should be implemented
			person = modelDao.fetchPersonalInformation(NetworkDAO.request(URIDataConstants.LINK_JASON_DATA));
		}
		
		String viewName = "person";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("person", person);
		model.put("numberToSave", person.size());
		
		return new ModelAndView(viewName, model);
	}
	
	@GetMapping("/addPersonForm")
	public ModelAndView showPersonInfoForm(@RequestParam (required = false) Integer id) {
		
		String viewName = "addPersonForm";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		PersonalInformation personToUpdate = findExistingPerson(id);
		
		if (personToUpdate == null) {
			model.put("personInfo", new PersonalInformation());	
			
		} else {
			model.put("personInfo", personToUpdate);
		}
		
		return new ModelAndView(viewName, model);
	}
	
	private PersonalInformation findExistingPerson(Integer id) {
		
		for(PersonalInformation person: person) {
			// TODO: Question to Nick: Should I always use Integer instead of int, Integer seems to has built in methods.
			if(person.getId().equals(id)) {
				return person;
			}
		}
		return null;
	}

	@PostMapping("/addPersonForm")
	public ModelAndView submitPersonInfoForm(PersonalInformation personInfo) {
		
		PersonalInformation existingPerson = findExistingPerson(personInfo.getId());
		
		if (existingPerson == null) {
			personInfo.setId(person.size() + 1);
			person.add(personInfo);
			
		} else {
			existingPerson.setFirstName(personInfo.getFirstName());
			existingPerson.setLastName(personInfo.getLastName());
			existingPerson.setAddress(personInfo.getFirstName());
			existingPerson.setCity(personInfo.getFirstName());
			existingPerson.setZip(personInfo.getFirstName());
			existingPerson.setPhone(personInfo.getFirstName());
			existingPerson.setEmail(personInfo.getFirstName());
		}
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/person");
		
		return new ModelAndView(redirect);
	}
}
