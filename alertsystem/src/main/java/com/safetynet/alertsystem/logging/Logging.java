package com.safetynet.alertsystem.logging;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;


@Aspect
public class Logging {
	private static final Logger logger = LogManager.getLogger("Logging");
   /** 
    * This is the method which I would like to execute
    * after a selected method execution.
    */
	@AfterReturning(value = ("within(com.safetynet.alertsystem.controller.RestEndController)"),
            returning = "returnValue")
    public void endpointAfterReturning(JoinPoint p, Object returnValue) {
        //if (logger.isTraceEnabled()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            try {
                logger.trace("\nResponse object: \n" + mapper.writeValueAsString(returnValue));
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
            }
            logger.trace(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " END");
        //}
    }
}