package com.safetynet.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlertController {
	
	@GetMapping("/person")
	public ModelAndView getPerson() {
		
		String viewName = "person";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("person", 1234);
		
		return new ModelAndView(viewName, model);
	}

}
