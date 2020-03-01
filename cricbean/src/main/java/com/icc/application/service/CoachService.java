package com.icc.application.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.application.model.Role;
import com.icc.application.model.User;
import com.icc.application.repositories.UserRepository;
import com.icc.application.dto.Coach;
import com.icc.application.exceptions.ResourceAlreadyExistsException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoachService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  AuthorityService authorityService;
	public void insert(Coach coach) {
		checkCoachInDb(coach);	
		checkCoachAndRoleInDb(coach);		
		User user= new User();
		user.setName(coach.getName());
		user.setPassword(coach.getPassword());
		user.setAge(coach.getAge());
		user.setDOB(coach.getDOB());

		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_COACH"));
		user.setRoles(roles);	
		
		
		user.setUsername(coach.getUsername());
		userRepository.save(user);
	}	
	public void update(Coach coach) {
		User user = userRepository.findById(coach.getId()).get();
		user.setName(coach.getName());
		user.setPassword(coach.getPassword());
		user.setAge(coach.getAge());
		user.setDOB(coach.getDOB());
		
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_COACH"));
		user.setRoles(roles);	
		
		userRepository.save(user);
	}
	public void delete(long id) {			
		userRepository.deleteById(id);
	}	
	public Coach get(long id) {
		User user = userRepository.findById(id).get();
		Coach coach= new Coach();
		coach.setName(user.getName());
		coach.setPassword(user.getPassword());
		coach.setAge(user.getAge());
		coach.setDOB(user.getDOB());
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_COACH"));
		coach.setRoles(roles);	
		
		coach.setUsername(user.getUsername());
		return coach;
	}
	public List<Coach> getAll() {
		List<Coach> coachs = new ArrayList<Coach>(); 
		for (User user : userRepository.findAll()) 
		{ 
			Coach coach= new Coach();
			coach.setId(user.getId());
			coach.setAge(user.getAge());
			coach.setDOB(user.getDOB());
			coach.setName(user.getName());
			
			Set<Role> roles = new HashSet<>();
			roles.add(authorityService.findByRoleName("ROLE_COACH"));
			coach.setRoles(roles);	
			
			coach.setUsername(user.getUsername());
			coachs.add(coach);			
		}
		return coachs;
	}
	private void checkCoachInDb(Coach c) {
		User user = userRepository.findByUsername(c.getUsername()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name already exists");
		}
	}
	private void checkCoachAndRoleInDb(Coach c) {
		//User user = userRepository.findByUsernameAndRole(c.getUsername(),"ROLE_COACH").get();
		User user = userRepository.findByUsername(c.getUsername()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name with same role already exists");
		}
	}
	
}
