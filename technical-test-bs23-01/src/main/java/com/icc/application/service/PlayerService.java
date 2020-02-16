package com.icc.application.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icc.application.model.User;
import com.icc.application.repositories.UserRepository;
import com.icc.application.dto.Player;
import com.icc.application.exceptions.ResourceAlreadyExistsException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerService {

	@Autowired
	private UserRepository userRepository;
	public void insert(Player player) {
		checkPlayerInDb(player);	
		checkPlayerAndRoleInDb(player);		
		User user= new User();
		user.setName(player.getName());
		user.setPassword(player.getPassword());
		user.setAge(player.getAge());
		user.setDOB(player.getDOB());
		user.setRole(player.getRole());
		user.setUsername(player.getUsername());
		userRepository.save(user);
	}	
	public void update(Player player) {
		User user = userRepository.findById(player.getId()).get();
		user.setName(player.getName());
		user.setPassword(player.getPassword());
		user.setAge(player.getAge());
		user.setDOB(player.getDOB());
		user.setRole(player.getRole());				
		userRepository.save(user);
	}
	public void delete(long id) {			
		userRepository.deleteById(id);
	}	
	public Player get(long id) {
		User user = userRepository.findById(id).get();
		Player player= new Player();
		player.setName(user.getName());
		player.setPassword(user.getPassword());
		player.setAge(user.getAge());
		player.setDOB(user.getDOB());
		player.setRole(user.getRole());
		player.setUsername(user.getUsername());
		return player;
	}
	public List<Player> getAll() {
		List<Player> players = new ArrayList<Player>(); 
		for (User user : userRepository.findAll()) 
		{ 
			Player player= new Player();
			player.setId(user.getId());
			player.setAge(user.getAge());
			player.setDOB(user.getDOB());
			player.setName(user.getName());
			player.setRole(user.getRole());
			player.setUsername(user.getUsername());
			players.add(player);			
		}
		return players;
	}
	private void checkPlayerInDb(Player c) {
		User user = userRepository.findByUsername(c.getUsername()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name already exists");
		}
	}
	private void checkPlayerAndRoleInDb(Player c) {
		User user = userRepository.findByUsernameAndRole(c.getUsername(),c.getRole().toString()).get();
		if (user != null) {
			throw new ResourceAlreadyExistsException("Same user name with same role already exists");
		}
	}
	
}
