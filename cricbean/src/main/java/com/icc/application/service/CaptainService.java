package com.icc.application.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icc.application.model.Country;
import com.icc.application.model.Role;
import com.icc.application.model.User;
import com.icc.application.repositories.UserRepository;
import com.icc.application.dto.Captain;
import com.icc.application.exceptions.ResourceAlreadyExistsException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CaptainService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  AuthorityService authorityService;
	
	public void insert(Captain captain) {
		checkCaptainInDb(captain);	
		checkCaptainAndRoleInDb(captain);		
		User user= new User();
		user.setName(captain.getName());
		user.setPassword(captain.getPassword());
		user.setAge(captain.getAge());
		user.setDOB(captain.getDOB());
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_CAPTAIN"));
		user.setRoles(roles);		
		
		
		user.setUsername(captain.getUsername());
		userRepository.save(user);
	}	
	public void update(Captain captain) {
		User user = userRepository.findById(captain.getId()).get();
		user.setName(captain.getName());
		user.setPassword(captain.getPassword());
		user.setAge(captain.getAge());
		user.setDOB(captain.getDOB());
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_CAPTAIN"));
		user.setRoles(roles);	
		
		userRepository.save(user);
	}
	public void delete(long id) {			
		userRepository.deleteById(id);
	}	
	public Captain get(long id) {
		User user = userRepository.findById(id).get();
		Captain captain= new Captain();
		captain.setName(user.getName());
		captain.setPassword(user.getPassword());
		captain.setAge(user.getAge());
		captain.setDOB(user.getDOB());
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_CAPTAIN"));
		captain.setRoles(roles);	
		
		captain.setUsername(user.getUsername());
		return captain;
	}
	public List<Captain> getAll() {
		List<Captain> teams = new ArrayList<Captain>(); 
		for (User user : userRepository.findAll()) 
		{ 
			Captain captain= new Captain();
			captain.setId(user.getId());
			captain.setAge(user.getAge());
			captain.setDOB(user.getDOB());
			captain.setName(user.getName());

			Set<Role> roles = new HashSet<>();
			roles.add(authorityService.findByRoleName("ROLE_CAPTAIN"));
			captain.setRoles(roles);	
			
			captain.setUsername(user.getUsername());
			teams.add(captain);			
		}
		return teams;
	}
	private void checkCaptainInDb(Captain c) {
		User user = userRepository.findByUsername(c.getUsername()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name already exists");
		}
	}
	private void checkCaptainAndRoleInDb(Captain c) {
		User user = userRepository.findByUsername(c.getUsername()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name with same role already exists");
		}
	}
	
}
