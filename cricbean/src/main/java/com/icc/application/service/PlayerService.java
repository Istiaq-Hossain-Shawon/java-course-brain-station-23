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
import com.icc.application.dto.PlayerDto;
import com.icc.application.exceptions.ResourceAlreadyExistsException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  AuthorityService authorityService;
	public void insert(PlayerDto player) {
		checkPlayerInDb(player);	
		checkPlayerAndRoleInDb(player);		
		User user= new User();
		user.setName(player.getName());
		user.setPassword(player.getPassword());
		user.setAge(player.getAge());
		user.setDOB(player.getDOB());
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_PLAYER"));
		user.setRoles(roles);	
		
		
		user.setUsername(player.getUsername());
		userRepository.save(user);
	}	
	public void update(PlayerDto player) {
		User user = userRepository.findById(player.getId()).get();
		user.setName(player.getName());
		user.setPassword(player.getPassword());
		user.setAge(player.getAge());
		user.setDOB(player.getDOB());
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_PLAYER"));
		user.setRoles(roles);	
		
		userRepository.save(user);
	}
	public void delete(long id) {			
		userRepository.deleteById(id);
	}	
	public PlayerDto get(long id) {
		User user = userRepository.findById(id).get();
		PlayerDto player= new PlayerDto();
		player.setName(user.getName());
		player.setPassword(user.getPassword());
		player.setAge(user.getAge());
		player.setDOB(user.getDOB());
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("ROLE_PLAYER"));
		player.setRoles(roles);	
		
		player.setUsername(user.getUsername());
		return player;
	}
	public List<PlayerDto> getAll() {
		List<PlayerDto> players = new ArrayList<PlayerDto>(); 
		for (User user : userRepository.findAll()) 
		{ 
			PlayerDto player= new PlayerDto();
			player.setId(user.getId());
			player.setAge(user.getAge());
			player.setDOB(user.getDOB());
			player.setName(user.getName());
			
			Set<Role> roles = new HashSet<>();
			roles.add(authorityService.findByRoleName("ROLE_PLAYER"));
			player.setRoles(roles);	
			
			player.setUsername(user.getUsername());
			players.add(player);			
		}
		return players;
	}
	private void checkPlayerInDb(PlayerDto c) {
		User user = userRepository.findByUsername(c.getUsername()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name already exists");
		}
	}
	private void checkPlayerAndRoleInDb(PlayerDto c) {
		//User user = userRepository.findByUsernameAndRole(c.getUsername(),"ROLE_PLAYER").get();
		User user = userRepository.findByUsername(c.getUsername()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name with same role already exists");
		}
	}
	
}
