package com.safetynet.alertsystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	
	public JSONObject getJsonData() {
		
		JSONObject personalInfoList = null;
		
		  //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("data.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            personalInfoList = (JSONObject) obj; 
             
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
		return personalInfoList;
	}
	
}
