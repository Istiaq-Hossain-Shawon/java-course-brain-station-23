package com.spring.practice.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.practice.model.Course;

import com.spring.practice.service.CourseService;


@Controller
public class CourseController {

	@Autowired	ServletContext context;
	@Autowired	 private CourseService courseService;	
	private static String UPLOADED_FOLDER = "/WEB-INF/resources/images/";
	
	@RequestMapping(value="/course/add",method = RequestMethod.GET)	
	public String add(Model model) {
		model.addAttribute("course", new Course());		
		model.addAttribute("message", "Please add a course");		
		return "course/add";
	}	
	@RequestMapping(value="/course/add",method = RequestMethod.POST)	
	public String add(@ModelAttribute(name = "course") Course course,@RequestParam("file") MultipartFile file,Model model) {
		if(course.getCourseName() == null || course.getCourseName().trim().isEmpty()) {
			throw new RuntimeException("Please give course name");
		}
		if(course.getCourseCode() == null || course.getCourseCode().trim().isEmpty()) {
			throw new RuntimeException("Please give course code");
		}
		if(course.getSubmission_date() == null  ) {
			throw new RuntimeException("Please give course date");
		}
		
		if (file.isEmpty()) {
			 throw new RuntimeException("Please select a file to upload");
		}		 
		 try {
			 byte[] bytes = file.getBytes();
			 String absoluteFilePath = context.getRealPath(UPLOADED_FOLDER);
			 Path path = Paths.get(absoluteFilePath + file.getOriginalFilename());
	         Files.write(path, bytes);
	         course.setCourseImage(file.getOriginalFilename());
	         courseService.insert(course);
	         model.addAttribute("message", "Course added successfully");
	         return "redirect:/course/show-all";
	    }catch (IOException e) {
	        	throw new RuntimeException(e.getMessage());
	    }		
	}	
	@RequestMapping(value="/course/show-all",method = RequestMethod.GET)	
	public String Get(Model model) {
		model.addAttribute("courses", courseService.getAll());
		model.addAttribute("message", "Showing all courses");
		model.addAttribute("filePath", UPLOADED_FOLDER);
		return "course/show-all";
	}
	@RequestMapping(value="/course/detail/{id}",method = RequestMethod.GET)	
	public String Get(Model model,@PathVariable("id") long id) {
		Course course=courseService.getById(id);
		model.addAttribute("course", course);
		model.addAttribute("message", "Showing the course");
		return "course/course-detail";	
	}


	
	
	 
}
