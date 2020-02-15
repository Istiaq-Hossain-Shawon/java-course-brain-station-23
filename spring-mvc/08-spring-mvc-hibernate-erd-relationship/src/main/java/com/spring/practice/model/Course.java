package com.spring.practice.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring5.practice.model.Student;

@Entity
@Table(name = "tbl_course")
public class Course implements Serializable{

	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long courseId;
	
	@Column(name = "courseCode")
	private String courseCode;
	
	@Column(name = "courseName")
	private String courseName;
	
	@Column(name = "courseDuration")
	private String courseDuration;
	
	@Column(name = "courseAuthor")
	private String courseAuthor;
	
	@Column(name = "courseImage")
	private String courseImage;
	
	@Column(name = "courseDescription")
	private String courseDescription;
	
	@Column(name = "courseLevel")
	private String courseLevel;
	
	@Column(name = "coursePrice")
	private double  coursePrice;
	
	@Column(name = "submission_date")
	private Date  submission_date;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class, optional = true)
	private Student student;
	
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
	
	public Course(long courseId, String courseName,String courseDuration,
			String courseAuthor,String courseImage,
			String courseDescription,String courseLevel,double coursePrice,Date submission_date) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseAuthor = courseAuthor;
		this.courseImage = courseImage;
		this.courseDescription = courseDescription;
		this.courseLevel = courseLevel;
		this.coursePrice = coursePrice;
		this.submission_date = submission_date;			
	}
	
	public Course() {
		super();
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
