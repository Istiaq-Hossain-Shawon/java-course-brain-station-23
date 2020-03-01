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
import com.icc.application.dto.IccEmployee;
import com.icc.application.exceptions.ResourceAlreadyExistsException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IccEmployeeService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  AuthorityService authorityService;
	
	public void insert(IccEmployee iccEmployee) {
		checkIccEmployeeInDb(iccEmployee);	
		checkIccEmployeeAndRoleInDb(iccEmployee);		
		User user= new User();
		user.setName(iccEmployee.getName());
		user.setPassword(iccEmployee.getPassword());
		user.setAge(iccEmployee.getAge());
		user.setDOB(iccEmployee.getDOB());
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_COACH"));
		user.setRoles(roles);	
		
		
		user.setUsername(iccEmployee.getUsername());
		userRepository.save(user);
	}	
	public void update(IccEmployee iccEmployee) {
		User user = userRepository.findById(iccEmployee.getId()).get();
		user.setName(iccEmployee.getName());
		user.setPassword(iccEmployee.getPassword());
		user.setAge(iccEmployee.getAge());
		user.setDOB(iccEmployee.getDOB());
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_COACH"));
		user.setRoles(roles);	
		
		
		userRepository.save(user);
	}
	public void delete(long id) {			
		userRepository.deleteById(id);
	}	
	public IccEmployee get(long id) {
		User user = userRepository.findById(id).get();
		IccEmployee iccEmployee= new IccEmployee();
		iccEmployee.setName(user.getName());
		iccEmployee.setPassword(user.getPassword());
		iccEmployee.setAge(user.getAge());
		iccEmployee.setDOB(user.getDOB());
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_COACH"));
		iccEmployee.setRoles(roles);	
		
		
		iccEmployee.setUsername(user.getUsername());
		return iccEmployee;
	}
	public List<IccEmployee> getAll() {
		List<IccEmployee> teams = new ArrayList<IccEmployee>(); 
		for (User user : userRepository.findAll()) 
		{ 
			IccEmployee iccEmployee= new IccEmployee();
			iccEmployee.setId(user.getId());
			iccEmployee.setAge(user.getAge());
			iccEmployee.setDOB(user.getDOB());
			iccEmployee.setName(user.getName());
			
			Set<Role> roles = new HashSet<>();
			roles.add(authorityService.findByRoleName("ROLE_COACH"));
			iccEmployee.setRoles(roles);	
			
			iccEmployee.setUsername(user.getUsername());
			teams.add(iccEmployee);			
		}
		return teams;
	}
	private void checkIccEmployeeInDb(IccEmployee c) {
		User user = userRepository.findByUsername(c.getUsername()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name already exists");
		}
	}
	private void checkIccEmployeeAndRoleInDb(IccEmployee c) {
		//User user = userRepository.findByUsernameAndRole(c.getUsername(),"ROLE_ICC_EMPLOYEE").get();
		User user = userRepository.findByUsername(c.getUsername()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name with same role already exists");
		}
	}
	
}
