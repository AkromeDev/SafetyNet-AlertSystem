package com.safetynet.alertsystem.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static final Logger logger = LogManager.getLogger("AlertController");
	
	private AlertService alertService;
	
	@Autowired
	public AlertController(AlertService alertService) {
		super();
		this.alertService = alertService;
	}
	
	@GetMapping("/personRender")
	public ModelAndView getPersonalInfo() throws ClientProtocolException, IOException {
		
		logger.info("HTTP GET request recieved at /personRender URL");
		
		String viewName = "personRender";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("personRender", alertService.addDataFromJson());
		model.put("numberToSave", alertService.getListSize());
		
		return new ModelAndView(viewName, model);
	}
	
	@GetMapping("/addPersonForm")
	public ModelAndView showPersonInfoForm(@RequestParam(required = false) Integer id) {
		
		logger.info("HTTP GET request recieved at /addPersonForm URL");
		
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
		
		logger.info("HTTP POST request recieved at /addPersonForm URL");
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("addPersonForm");
		}
		
		alertService.addOrUpdtePersonInfo(personInfo);
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/personRender");
		
		return new ModelAndView(redirect);
	}
}
