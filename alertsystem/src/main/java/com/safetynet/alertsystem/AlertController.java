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
	public ModelAndView showPersonInfoForm() {
		
		String viewName = "addPersonForm";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("personInfo", new PersonalInformation());
		
		return new ModelAndView(viewName, model);
	}
	
	@PostMapping("/addPersonForm")
	public ModelAndView submitPersonInfoForm(PersonalInformation personInfo) {
		
		personInfo.setId(person.size() + 1);
		person.add(personInfo);
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/person");
		
		return new ModelAndView(redirect);
	}
}
