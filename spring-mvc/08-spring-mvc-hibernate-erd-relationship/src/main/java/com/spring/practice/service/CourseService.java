package com.spring.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
		
		CriteriaBuilder cb = hibernateConfig.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery( Course.class );
        Root<Course> root = cq.from(Course.class);
        cq.where(cb.equal( root.get( "courseId" ), courseId ));
        List<Course> result = hibernateConfig.getSession().getEntityManagerFactory().createEntityManager().createQuery(cq).getResultList();
        if(result.isEmpty()) {				
			throw new RuntimeException("No Course found");
		}	
		return result.get(0);
	}
	public boolean UpdateById(long courseId,Course course) {		
		Session session = hibernateConfig.getSession();		
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
		CriteriaBuilder cb = hibernateConfig.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery( Course.class );
        Root<Course> root = cq.from(Course.class);       
        List<Course> result = hibernateConfig.getSession().getEntityManagerFactory().createEntityManager().createQuery(cq).getResultList();        	
		return result;	
	}
	private void isDuplicate(Course c) {		
		if (c.getCourseCode() == "" || c.getCourseCode() == null) {
			throw new RuntimeException("Course code is null or empty.");
		}
		CriteriaBuilder cb = hibernateConfig.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery( Course.class );
        Root<Course> root = cq.from(Course.class);
        cq.where(cb.equal( root.get( "courseCode" ), c.getCourseCode() ));
        List<Course> result = hibernateConfig.getSession().getEntityManagerFactory().createEntityManager().createQuery(cq).getResultList();        	
		if(!result.isEmpty()) {				
			throw new RuntimeException("Course code already exist");
		}
		
	}

}
