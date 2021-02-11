package com.fullcrudoperations.soap.webservices.soapcoursemanagement.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fullcrudoperations.soap.webservices.soapcoursemanagement.soap.bean.Course;

@Component
public class CourseDetailsService {

	public enum Course_Status{
		SUCCESS ,
		FALIURE 
	}
	private static List<Course> courses = new ArrayList<Course>();
	
	static {
		Course course1 = new Course (1 , "Spring" , "10 steps");
		courses.add(course1);
		Course course2 = new Course (2 , "Spring MVC" , "10 examples");
		courses.add(course2);
		Course course3 = new Course (3 , "Spring Boot" , "6k steps");
		courses.add(course3);
		Course course4 = new Course (4 , "maven" , "1 million student");
		courses.add(course4);
	}
	
	//getCourseDetails	
		public Course findById(int id) {
			for (Course course : courses) {
				if (course.getId() == id) return course;
			}
			return null;
		}
	
	
	
	
	//getAllCoursesDetails
		public List<Course> findAll(){
			return courses;
		}
		
		
	//DeleteAllCourses
	public Course_Status DeleteCourse(int id) {
		Iterator<Course> iterator = courses.iterator(); // 1	 2 3 4
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
				return Course_Status.SUCCESS ;
			}
		}
		return Course_Status.FALIURE ;
	}
	
	
	//AddCourse
	public String AddCourse (Course c) {
		if (courses.add(c))
		return "Added";
		else 
			return "Failed";
	}
	
	//updateCourse 
	public Course UpdateCourse (Course c) {
		Iterator<Course> iterator = courses.iterator();
		for (Course course : courses) {
			if (c.getId() == course.getId()) {
				course.setId(c.getId());
				course.setName(c.getName());
				course.setDescription(c.getDescription());
				return course ;
			}
		}
		courses.add(c);
		return c ;
	}
}
