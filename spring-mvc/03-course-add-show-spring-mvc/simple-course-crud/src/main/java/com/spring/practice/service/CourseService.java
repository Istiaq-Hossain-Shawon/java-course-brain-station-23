package com.spring.practice.service;

import java.util.ArrayList;
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

	private void addCourse(String countryName) {
		Course courseObj = new Course();
		courseObj.setCourseId(courses.size() + 1);
		courseObj.setCourseName(countryName);		
		courses.add(courseObj);
	}

	public void addCourse(Course country) {
		checkCourseInList(country);
		country.setCourseId(courses.size() + 1);
		courses.add(country);
	}

	public void checkCourseInList(Course c) {
		if (courses.stream().filter(course -> course.getCourseName().equals(c.getCourseName())).findAny()
				.isPresent()) {
			throw new RuntimeException("Course already exists in list");
		}
	}

	public Course getCourseByName(String courseName) {

		//return new Course();
		return courses.stream().filter(course -> course.getCourseName().equals(courseName)).findAny()
				.orElseThrow(() -> new RuntimeException("Course not found with this code"));

	}

	public List<Course> getAll() {
		return courses;
	}
}
