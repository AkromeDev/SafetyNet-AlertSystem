package com.safetynet.alertsystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
	
	public List<PersonalInformation> getJsonData() {
		
		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json")) {
			
			// Read JSON file
			Object obj = jsonParser.parse(reader);
			
			JSONArray personalInfoList = (JSONArray) obj;
			System.out.println(personalInfoList);
			
		} catch (FileNotFoundException e) {
			// TODO add a logger to all the exceptions with a good text location
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
