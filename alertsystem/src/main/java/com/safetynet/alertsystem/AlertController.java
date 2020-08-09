package com.safetynet.alertsystem;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.safetynet.dao.ModelDAO;

import Model.PersonalInformation;

@Controller
public class AlertController {
	
	private List<PersonalInformation> personalInfo;
	private List<PersonalInformation> medicalRecords;
	private List<PersonalInformation> firestations;
	
	@GetMapping("/personalInfo")
	public ModelAndView getPersonalInfo() throws ClientProtocolException, IOException {
		
		ModelDAO modelDao = new ModelDAO();
		
		personalInfo = modelDao.fetchPersonalInformation("one");
		
		String viewName = "person";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("personInfo", 1234);
		
		return new ModelAndView(viewName, model);
	}
}
