package com.icc.application.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icc.application.model.Country;
import com.icc.application.model.User;
import com.icc.application.repositories.UserRepository;
import com.icc.application.dto.TeamManager;
import com.icc.application.exceptions.ResourceAlreadyExistsException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamManagerService {

	@Autowired
	private UserRepository userRepository;
	public void insert(TeamManager teamManager) {
		checkTeamManagerInDb(teamManager);	
		checkTeamManagerAndRoleInDb(teamManager);		
		User user= new User();
		user.setName(teamManager.getName());
		user.setPassword(teamManager.getPassword());
		user.setAge(teamManager.getAge());
		user.setDOB(teamManager.getDOB());
		user.setRole(teamManager.getRole());
		user.setUsername(teamManager.getUsername());
		userRepository.save(user);
	}	
	public void update(TeamManager teamManager) {
		User user = userRepository.findById(teamManager.getId()).get();
		user.setName(teamManager.getName());
		user.setPassword(teamManager.getPassword());
		user.setAge(teamManager.getAge());
		user.setDOB(teamManager.getDOB());
		user.setRole(teamManager.getRole());				
		userRepository.save(user);
	}
	public void delete(long id) {			
		userRepository.deleteById(id);
	}	
	public TeamManager get(long id) {
		User user = userRepository.findById(id).get();
		TeamManager teamManager= new TeamManager();
		teamManager.setName(user.getName());
		teamManager.setPassword(user.getPassword());
		teamManager.setAge(user.getAge());
		teamManager.setDOB(user.getDOB());
		teamManager.setRole(user.getRole());
		teamManager.setUsername(user.getUsername());
		return teamManager;
	}
	public List<TeamManager> getAll() {
		List<TeamManager> teams = new ArrayList<TeamManager>(); 
		for (User user : userRepository.findAll()) 
		{ 
			TeamManager teamManager= new TeamManager();
			teamManager.setId(user.getId());
			teamManager.setAge(user.getAge());
			teamManager.setDOB(user.getDOB());
			teamManager.setName(user.getName());
			teamManager.setRole(user.getRole());
			teamManager.setUsername(user.getUsername());
			teams.add(teamManager);			
		}
		return teams;
	}
	private void checkTeamManagerInDb(TeamManager c) {
		User user = userRepository.findByUsername(c.getUsername()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name already exists");
		}
	}
	private void checkTeamManagerAndRoleInDb(TeamManager c) {
		User user = userRepository.findByUsernameAndRole(c.getUsername(),c.getRole().toString()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name with same role already exists");
		}
	}
	
}
