package com.spring.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.spring.practice.config.HibernateConfig;
import com.spring.practice.model.Course;


@Service
public class CourseService {

	
	private final HibernateConfig hibernateConfig;

	public CourseService(HibernateConfig hibernateConfig) {
		this.hibernateConfig=hibernateConfig;
	}
	public void insert(Course course) {
		isDuplicate(course);
		Session session = hibernateConfig.getSession();
		Transaction transaction = session.beginTransaction();		
		session.save(course);
		transaction.commit();
	}
	public Course getById(long courseId) {		
		Session session = hibernateConfig.getSession();
		Transaction transaction = session.beginTransaction();
		List<Course> course =session.getEntityManagerFactory() .createEntityManager()
		        .createQuery( "from  Course c where c.courseId=:courseId",Course.class)
		        .setParameter( "courseId", courseId)			        
		        .getResultList();	
		if(course.isEmpty()) {				
			throw new RuntimeException("No Course found");
		}	
		transaction.commit();
		return course.get(0);
	}
	public boolean UpdateById(long courseId,Course course) {		
		Session session = hibernateConfig.getSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("update course set courseName=:coursename where courseId=:courseid");
        query.setParameter("coursename", course.getCourseName());
        query.setParameter("courseid", courseId);
        Transaction t = session.beginTransaction();
        int result = query.executeUpdate();
        t.commit();
		if(result>0) {
			return true;
		}
		return false;
	}
	public boolean deleteById(long courseId) {		
		Session session = hibernateConfig.getSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from course where courseId=:courseid");
        query.setParameter("courseid", courseId);
        Transaction t = session.beginTransaction();
        int result = query.executeUpdate();        
        t.commit();
		if(result>0) {
			return true;
		}
		return false;
	}
	public List<Course> getAll() {
		Session session = hibernateConfig.getSession();
		Transaction transaction = session.beginTransaction();
		List<Course> course =session.getEntityManagerFactory().createEntityManager()
		        .createQuery( "SELECT c from  Course c ",Course.class)		        		        
		        .getResultList();
		return course;		
	}
	private void isDuplicate(Course c) {
		if (c.getCourseName() == "" || c.getCourseName() == null) {
			throw new RuntimeException("Course name is null or empty.");
		}
		if (c.getCourseCode() == "" || c.getCourseCode() == null) {
			throw new RuntimeException("Course code is null or empty.");
		}
		Session session = hibernateConfig.getSession();
		Transaction transaction = session.beginTransaction();
		List course =session.getEntityManagerFactory() .createEntityManager()
		        .createQuery( "from  Course c where c.courseCode=:coursecode")
		        .setParameter( "coursecode", c.getCourseCode() )			        
		        .getResultList();	
		if(!course.isEmpty()) {				
			throw new RuntimeException("Course code already exist");
		}	
		transaction.commit();
	}

}
