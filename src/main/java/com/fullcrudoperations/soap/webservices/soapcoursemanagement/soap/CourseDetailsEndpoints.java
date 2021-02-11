package com.fullcrudoperations.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.fullcrudoperations.courses.AddCourseDetailsRequest;
import com.fullcrudoperations.courses.AddCourseDetailsResponse;
import com.fullcrudoperations.courses.CourseDetails;
import com.fullcrudoperations.courses.DeleteCourseDetailsRequest;
import com.fullcrudoperations.courses.DeleteCourseDetailsResponse;
import com.fullcrudoperations.courses.GetAllCourseDetailsRequest;
import com.fullcrudoperations.courses.GetAllCourseDetailsResponse;
import com.fullcrudoperations.courses.GetCourseDetailsRequest;
import com.fullcrudoperations.courses.GetCourseDetailsResponse;
import com.fullcrudoperations.courses.Status;
import com.fullcrudoperations.courses.UpdateCourseDetailsRequest;
import com.fullcrudoperations.courses.UpdateCourseDetailsResponse;
import com.fullcrudoperations.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.fullcrudoperations.soap.webservices.soapcoursemanagement.soap.exception.CourseNotFoundException;
import com.fullcrudoperations.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import com.fullcrudoperations.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService.Course_Status;

@Endpoint
public class CourseDetailsEndpoints {
	
	@Autowired
	CourseDetailsService service;
	
	//method
	//GetCourseDetailsRequest is an input
	//GetCourseDetailsResponse is an output
	//support namespace :http://in28minutes.com/courses with name GetCourseDetailsRequest
	
	
	
	
	@PayloadRoot(namespace = "http://fullcrudoperations.com/courses" , localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processGetCourseDetailsRequest (
		@RequestPayload GetCourseDetailsRequest request) {
		
		Course course = service.findById(request.getId()); // course
		if (course == null)
			throw new CourseNotFoundException ("Invalid Course id " + request.getId());
		return mapCourseDetails(course);
		
	}
	

		
	@PayloadRoot(namespace = "http://fullcrudoperations.com/courses" , localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllGetCourseDetailsRequest (
		@RequestPayload GetAllCourseDetailsRequest request) {
		
		List<Course> courses = service.findAll(); // course
		return mapAllCourseDetails(courses);
		
	}
	
	
	@PayloadRoot(namespace = "http://fullcrudoperations.com/courses" , localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse processDeleteCourseDetailsRequest (
		@RequestPayload DeleteCourseDetailsRequest request) {
		
	
		Course_Status  status = service.DeleteCourse(request.getId()); // SUCCESS
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(status == Course_Status.FALIURE ? Status.FALIURE : Status.SUCCESS);
		return response;	
	}
	

	
	@PayloadRoot(namespace = "http://fullcrudoperations.com/courses" , localPart = "AddCourseDetailsRequest")
	@ResponsePayload
	public AddCourseDetailsResponse processAddCourseDetailsRequest (
		@RequestPayload AddCourseDetailsRequest request) {
		AddCourseDetailsResponse response = new AddCourseDetailsResponse();		
		CourseDetails course_details = request.getCourseDetails();
		Course course = new Course (course_details.getId() , course_details.getName() , course_details.getDescription());
		 response.setResult(service.AddCourse(course));
		 return response ;
	}
	
	
	@PayloadRoot(namespace = "http://fullcrudoperations.com/courses" , localPart = "UpdateCourseDetailsRequest")
	@ResponsePayload
	public UpdateCourseDetailsResponse processUpdateCourseDetailsRequest (
		@RequestPayload UpdateCourseDetailsRequest request) {
		
		UpdateCourseDetailsResponse response = new UpdateCourseDetailsResponse();	
		CourseDetails course_details = request.getCourseDetails(); // id , name , describtion	
		Course course = new Course (course_details.getId() , course_details.getName() , course_details.getDescription());
		  response.setCourseDetails(mapCourse(service.UpdateCourse(course)));
		  return response;
	}
	
	
	
	
	
	private GetCourseDetailsResponse mapCourseDetails (Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response ;
	}
	
	private GetAllCourseDetailsResponse mapAllCourseDetails (List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response ;
	}
	
	
	

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails ;
	}
	

}
