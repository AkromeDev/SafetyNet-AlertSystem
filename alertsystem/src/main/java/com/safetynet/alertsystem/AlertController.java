package com.safetynet.alertsystem;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.safetynet.constants.URIDataConstants;
import com.safetynet.dao.ModelDAO;
import com.safetynet.dao.NetworkDAO;

import Model.PersonalInformation;

@Controller
public class AlertController {
	
	private List<PersonalInformation> personalInfo;
	private List<PersonalInformation> medicalRecords;
	private List<PersonalInformation> firestations;
	
	@GetMapping("/personalInfo")
	public ModelAndView getPersonalInfo() throws ClientProtocolException, IOException {
		
		ModelDAO modelDao = new ModelDAO();
		
		// TODO: ask Nick where the code that chooses the data source should be implemented
		personalInfo = modelDao.fetchPersonalInformation(NetworkDAO.request(URIDataConstants.LINK_JASON_DATA));
		
		String viewName = "personalInfo";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("personalInfo", personalInfo);
		model.put("numberToSave", personalInfo.size());
		
		return new ModelAndView(viewName, model);
	}
}
