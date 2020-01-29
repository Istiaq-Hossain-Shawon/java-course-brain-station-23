package com.spring.practice.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CourseTag implements Serializable{

	private long tagId;
	private String tagName;
	private long courseId;
	public long getTagId() {
		return tagId;
	}
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	
	
}
