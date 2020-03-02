package com.icc.application.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.icc.application.model.Country;
import com.icc.application.model.Role;
import com.icc.application.model.User;
import com.icc.application.repositories.UserRepository;
import com.icc.application.dto.PlayerDto;
import com.icc.application.dto.TeamManager;
import com.icc.application.dto.TeamManagerDto;
import com.icc.application.exceptions.ResourceAlreadyExistsException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamManagerService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private  AuthorityService authorityService;
	public  User Add(TeamManagerDto teamManagerDto) {
		var user=userRepository.findByUsername(teamManagerDto.getUsername());
		if (!user.isEmpty()) {
			throw new ResourceAlreadyExistsException("User name already existed.");
		}
		User userObj = new User();
		userObj.setAge(teamManagerDto.getAge());
		userObj.setDOB(teamManagerDto.getDob());
		userObj.setLogo(teamManagerDto.getLogo());
		userObj.setName(teamManagerDto.getName());
		userObj.setPassword(passwordEncoder.encode(teamManagerDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_TEAM_MANAGER"));
		userObj.setRoles(roles);
		
		userObj.setUsername(teamManagerDto.getUsername());					
		userRepository.save(userObj);
		return userObj;
	}	
	public void update(TeamManager teamManager) {
		User user = userRepository.findById(teamManager.getId()).get();
		user.setName(teamManager.getName());
		user.setPassword(teamManager.getPassword());
		user.setAge(teamManager.getAge());
		user.setDOB(teamManager.getDOB());
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_TEAM_MANAGER"));
		user.setRoles(roles);	
		
		
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
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_TEAM_MANAGER"));
		teamManager.setRoles(roles);	
		
		
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
			

			Set<Role> roles = new HashSet<>();
			roles.add(authorityService.findByRoleName("ROLE_TEAM_MANAGER"));
			teamManager.setRoles(roles);	
			
			
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
		//User user = userRepository.findByUsernameAndRole(c.getUsername(),"ROLE_TEAM_MANAGER").get();
		User user = userRepository.findByUsername(c.getUsername()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name with same role already exists");
		}
	}
	
}
