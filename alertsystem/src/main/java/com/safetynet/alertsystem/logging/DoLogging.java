package com.safetynet.alertsystem.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class DoLogging implements LoggingService {
	
	private static final Logger logger = LogManager.getLogger("DoLogging");
	
	@Override
	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object body) {

		logger.info("Going to log the Body!!");
		logger.info(body.toString());
	}
	
	@Override
	public void logRequest(HttpServletRequest httpServletRequest, Object body) {
		// TODO Auto-generated method stub
		
	}

}
