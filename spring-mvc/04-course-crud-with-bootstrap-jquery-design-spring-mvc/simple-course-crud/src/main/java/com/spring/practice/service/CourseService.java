package com.spring.practice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import com.spring.practice.model.Course;


@Service
public class CourseService {

	private static List<Course> courses = new ArrayList<Course>();

	private static final String[] COURSES = { "Cloud Computing", "Computer Networks", "Data Structures & Algorithms", "Distributed Computing Systems", "Data Base Management Systems", "Design and Analysis of Algorithms", "Operating Systems (Unix Programming)" };

	public CourseService() {
		Stream.of(COURSES).forEach(course -> {
			addCourse(course);
		});
	}
	private void addCourse(String courseName) {
		Course courseObj = new Course();
		courseObj.setCourseId(courses.size() + 1);
		courseObj.setCourseName(courseName);
		courseObj.setCourseCode("001");
		courseObj.setCourseDuration("3h 33m");
		courseObj.setCourseAuthor("Istiaq");
		courseObj.setCourseImage("img-"+courseObj.getCourseId()+".jpg");
		courseObj.setCourseDescription("This Java Programming course provides extensive experience with Java and its object-oriented features. You use Java to create both console and GUI application");
		courseObj.setCourseLevel("Beginner");
		courseObj.setCoursePrice(11.99);		
		courseObj.setCourseTags(Arrays.asList(new String[]{"Java", "Spring Mvc", "C", "Hibernate"}));		
		courses.add(courseObj);
	}
	public void addCourse(Course course) {
		checkCourseInList(course);
		course.setCourseId(courses.size() + 1);
		course.setCourseImage("img-"+course.getCourseId()+".jpg");
		course.setCourseTags(Arrays.asList(new String[]{"Java", "Spring Mvc", "C", "Hibernate"}));	
		courses.add(course);
	}
	public void checkCourseInList(Course c) {
		
		if(c.getCourseName()=="" || c.getCourseName()==null) {
			throw new RuntimeException("Course name is null or empty.");			
		}
		if(c.getCourseCode()=="" || c.getCourseCode()==null) {
			throw new RuntimeException("Course code is null or empty.");			
		}		
		if (courses.stream().filter(course -> course.getCourseCode().equals(c.getCourseCode())).findAny()
				.isPresent()) {
			throw new RuntimeException("Course already exists in list");
		}
	}
	public Course getCourseByCode(String courseCode) {
		return courses.stream().filter(course -> course.getCourseCode().equals(courseCode)).findAny()
				.orElseThrow(() -> new RuntimeException("Course not found with this code"));

	}

	public List<Course> getAll() {
		return courses;
	}
}
