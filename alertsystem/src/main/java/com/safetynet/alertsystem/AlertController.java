package com.safetynet.alertsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import Model.PersonalInformation;

@Controller
public class AlertController {
	
	private List<PersonalInformation> personalInfo = new ArrayList<PersonalInformation>();
	private List<PersonalInformation> medicalRecords = new ArrayList<PersonalInformation>();
	private List<PersonalInformation> firestations = new ArrayList<PersonalInformation>();
	private static int index = 1;
	
	
	@GetMapping("/personalInfo")
	public ModelAndView getPersonalInfo() {
		
		personalInfo.add(new PersonalInformation("","","","","","","", index++));
		
		String viewName = "person";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("personInfo", 1234);
		
		return new ModelAndView(viewName, model);
	}
}
