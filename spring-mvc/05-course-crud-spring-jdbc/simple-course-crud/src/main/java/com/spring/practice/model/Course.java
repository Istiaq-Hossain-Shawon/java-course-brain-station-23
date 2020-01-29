package com.spring.practice.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Course implements Serializable{

	private long courseId;
	private String courseCode;
	private String courseName;
	private String courseDuration;
	private String courseAuthor;
	private String courseImage;
	private String courseDescription;
	private String courseLevel;
	private double  coursePrice;
	private Date  submission_date;
	private List<CourseTag> courseTags;
	
	
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getCourseId() {
		return this.courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + "]";
	}
	
	public Course(long courseId, String courseName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}
	
	public Course() {
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}
	public String getCourseAuthor() {
		return courseAuthor;
	}
	public void setCourseAuthor(String courseAuthor) {
		this.courseAuthor = courseAuthor;
	}
	public String getCourseImage() {
		return courseImage;
	}
	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	public String getCourseLevel() {
		return courseLevel;
	}
	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}
	public List<CourseTag> getCourseTags() {
		return courseTags;
	}
	public void setCourseTags(List<CourseTag> courseTags) {
		this.courseTags = courseTags;
	}
	public double  getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(double  coursePrice) {
		this.coursePrice = coursePrice;
	}
	public Date getSubmission_date() {
		return submission_date;
	}
	public void setSubmission_date(Date submission_date) {
		this.submission_date = submission_date;
	}
	
}
