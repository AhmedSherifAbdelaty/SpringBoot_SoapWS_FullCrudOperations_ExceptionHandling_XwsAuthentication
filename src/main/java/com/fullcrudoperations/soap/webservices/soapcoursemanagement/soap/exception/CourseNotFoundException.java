package com.fullcrudoperations.soap.webservices.soapcoursemanagement.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode=FaultCode.CUSTOM , customFaultCode="{http://fullcrudoperations.com/courses}001")
public class CourseNotFoundException extends RuntimeException {

	
	public CourseNotFoundException(String message) {
		super(message);
	}

	
	
}
