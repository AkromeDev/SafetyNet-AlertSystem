package com.safetynet.alertsystem.httptrace;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Component
public class HttpRequestLogger extends AbstractRequestLoggingFilter {
	
	private static final Logger logger = LogManager.getLogger("HttpRequestLogger");

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		logger.debug("Before request Starts:");
		logger.debug("Request URL : " + request.getRequestURL().toString());
		
		try {
			if ("POST".equalsIgnoreCase(request.getMethod())) {
				   String test = request.getInputStream().toString();
				   logger.debug(test);
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		// TODO Auto-generated method stub
		
	}
	
}