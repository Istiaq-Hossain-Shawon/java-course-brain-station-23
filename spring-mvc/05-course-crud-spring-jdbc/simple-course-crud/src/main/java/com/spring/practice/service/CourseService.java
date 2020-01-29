package com.spring.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.spring.practice.model.Course;
import com.spring.practice.model.CourseTag;

@Service
public class CourseService {

	private final JdbcTemplate jdbcTemplate;

	public CourseService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		String CreateCourseTableIfNotExist = "create table if not exists Course(\n" + 
				"  courseId INT NOT NULL AUTO_INCREMENT,\n" + 
				"  courseCode VARCHAR(100) NOT NULL,\n" + 
				"  courseName VARCHAR(40) NOT NULL,\n" + 
				"  submission_date DATE,\n" + 
				"  courseDuration VARCHAR(40),\n" + 
				"  courseAuthor VARCHAR(40),\n" + 
				"  courseImage VARCHAR(40),\n" + 
				"  courseDescription VARCHAR(40),\n" + 
				"  courseLevel VARCHAR(40),\n" + 
				"  coursePrice DOUBLE(5, 2),\n" + 
				"  PRIMARY KEY (courseId)\n" + 
				");";
		String CreateTagTableIfNotExist = "create table if not exists CourseTag(\n" + 
				"  tagId INT NOT NULL AUTO_INCREMENT,\n" + 
				"  tagName VARCHAR(100) NOT NULL,\n" + 
				"  courseId int NOT NULL,\n" + 
				"  PRIMARY KEY (tagId)\n" + 
				");\n" + 
				"";
		this.jdbcTemplate.execute(CreateCourseTableIfNotExist);
		this.jdbcTemplate.execute(CreateTagTableIfNotExist);
	}

	public void addCourse(Course course) {
		checkCourseInList(course);
		String insertQuery = "insert into Course("
				+ "courseCode,courseName,submission_date,courseDuration,courseAuthor,courseImage,courseDescription,courseLevel,coursePrice) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?); ";
		int resultSet = jdbcTemplate.update(insertQuery, course.getCourseCode(), course.getCourseName(),
				course.getSubmission_date(), course.getCourseDuration(), course.getCourseAuthor(),
				course.getCourseImage(), course.getCourseDescription(), course.getCourseLevel(),
				course.getCoursePrice());
		if (resultSet < 1) {
			throw new RuntimeException("Failed to create course.");
		} else {
			if(course.getCourseTags()!=null) {
				for (CourseTag ctags : course.getCourseTags()) {
					addCourseTags(ctags);
				}
			}			
		}
	}

	public void addCourseTags(CourseTag coursetag) {
		String insertQuery = "insert into CourseTag(" + "tagName,courseId) values(?, ?); ";
		int resultSet = jdbcTemplate.update(insertQuery, coursetag.getTagName(), coursetag.getCourseId());
		if (resultSet < 1) {
			throw new RuntimeException("Failed to create course tag.");
		}
	}

	public void checkCourseInList(Course c) {
		if (c.getCourseName() == "" || c.getCourseName() == null) {
			throw new RuntimeException("Course name is null or empty.");
		}
		if (c.getCourseCode() == "" || c.getCourseCode() == null) {
			throw new RuntimeException("Course code is null or empty.");
		}
		String checkCountryQuery = "select count(*) as count from course c where c.courseCode = ?; ";
		Map<String, Object> map = jdbcTemplate.queryForMap(checkCountryQuery, c.getCourseCode());
		if (Integer.parseInt(map.get("count").toString()) > 0) {
			throw new RuntimeException("Course with this course code is already in DB.");
		}
	}

	public Course getCourseByCode(String courseCode) {
		String countryByCode = "select * from course where courseCode = ? ; ";
		Map<String, Object> countryMap = jdbcTemplate.queryForMap(countryByCode, courseCode);
		Course course = new Course();
		course.setCourseId(Long.parseLong(countryMap.get("courseId").toString()));
		course.setCourseName(countryMap.get("courseName").toString());
		course.setCourseCode(countryMap.get("courseCode").toString());
		course.setCourseDuration(countryMap.get("courseDuration").toString());
		course.setCourseAuthor(countryMap.get("courseAuthor").toString());
		course.setCourseImage(countryMap.get("courseImage").toString());
		course.setCourseDescription(countryMap.get("courseDescription").toString());
		course.setCourseLevel(countryMap.get("courseLevel").toString());
		course.setCoursePrice(Double.parseDouble(countryMap.get("coursePrice").toString()));
		course.setCourseTags(getAllCourseTagByCourseId(course.getCourseId()));
		return course;
	}

	public List<CourseTag> getAllCourseTagByCourseId(long courseId) {
		String courseTagByCourseId = "select * from coursetag where courseId = ? ; ";
		List<CourseTag> coursetags = new ArrayList<CourseTag>();
		jdbcTemplate.queryForList(courseTagByCourseId, courseId).stream().forEach(courseTagMap -> {
			CourseTag coursetag = new CourseTag();
			coursetag.setTagId(Long.parseLong(courseTagMap.get("tagId").toString()));
			coursetag.setTagName(courseTagMap.get("tagName").toString());
			coursetag.setCourseId(Long.parseLong(courseTagMap.get("courseId").toString()));
			coursetags.add(coursetag);
		});
		return coursetags;
	}

	public List<Course> getAll() {
		String allCourseQuery = "select * from Course; ";
		List<Course> courses = new ArrayList<Course>();
		jdbcTemplate.queryForList(allCourseQuery).stream().forEach(courseMap -> {
			Course course = new Course();
			course.setCourseId(Long.parseLong(courseMap.get("courseId").toString()));
			course.setCourseName(courseMap.get("courseName").toString());
			course.setCourseCode(courseMap.get("courseCode").toString());
			course.setCourseDuration(courseMap.get("courseDuration").toString());
			course.setCourseAuthor(courseMap.get("courseAuthor").toString());
			course.setCourseImage(courseMap.get("courseImage").toString());
			course.setCourseDescription(courseMap.get("courseDescription").toString());
			course.setCourseLevel(courseMap.get("courseLevel").toString());
			course.setCoursePrice(Double.parseDouble(courseMap.get("coursePrice").toString()));
			course.setCourseTags(getAllCourseTagByCourseId(course.getCourseId()));
			courses.add(course);
		});

		return courses;
		/* return courses; */
	}
}
