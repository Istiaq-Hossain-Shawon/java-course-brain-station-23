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
@Table(name = "tbl_student_course")
public class StudentCourse implements Serializable{

	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long courseId;	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class, optional = true)
	private Student student;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class, optional = true)
	private Course course;
	
	
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getCourseId() {
		return this.courseId;
	}
	
	
	public StudentCourse() {
		super();
	}
	
	
}
