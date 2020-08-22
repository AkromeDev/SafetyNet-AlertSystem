package com.safetynet.alertsystem.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.safetynet.alertsystem.model.PersonalInformation;
import com.safetynet.alertsystem.service.AlertService;

@Controller
public class AlertController {
	
	private List<PersonalInformation> medicalRecords;
	private List<PersonalInformation> firestations;
	
	private AlertService alertService;
	
	@Autowired
	public AlertController(AlertService alertService) {
		super();
		this.alertService = alertService;
	}

	@GetMapping("/person")
	public ModelAndView getPersonalInfo() throws ClientProtocolException, IOException {
		
		String viewName = "person";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("person", alertService.addDataFromJson());
		model.put("numberToSave", alertService.getListSize());
		
		return new ModelAndView(viewName, model);
	}
	
	@GetMapping("/addPersonForm")
	public ModelAndView showPersonInfoForm(@RequestParam(required = false) Integer id) {
		
		String viewName = "addPersonForm";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		PersonalInformation personToUpdate = alertService.findPersonByID(id);
		
		if (personToUpdate == null) {
			model.put("personInfo", new PersonalInformation());	
			
		} else {
			model.put("personInfo", personToUpdate);
		}
		
		return new ModelAndView(viewName, model);
	}
	

	@PostMapping("/addPersonForm")
	public ModelAndView submitPersonInfoForm(@Valid @ModelAttribute("personInfo") PersonalInformation personInfo, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("addPersonForm");
		}
		
		alertService.addOrUpdtePersonInfo(personInfo);
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/person");
		
		return new ModelAndView(redirect);
	}
}
