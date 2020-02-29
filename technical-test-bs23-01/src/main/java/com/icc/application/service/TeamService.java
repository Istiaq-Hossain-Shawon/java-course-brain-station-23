package com.icc.application.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.icc.application.exceptions.ResourceAlreadyExistsException;
import com.icc.application.model.Country;
import com.icc.application.model.Role;
import com.icc.application.model.Team;
import com.icc.application.model.User;
import com.icc.application.dto.PlayerDto;
import com.icc.application.dto.TeamDto;
import com.icc.application.repositories.CountryRepository;
import com.icc.application.repositories.TeamRepository;
import com.icc.application.repositories.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	AuthorityService authorityService;
	

	public void insert(TeamDto teamdto) {
		checkTeamInDb(teamdto);
		Country country=countryRepository.findByCountryId(teamdto.getCountryId());
		if (country == null) {
			throw new ResourceAlreadyExistsException("Invalid Country.");
		}
		Team team = new Team();
		team.setName(teamdto.getName());
		team.setLogo(teamdto.getLogo());
		team.setStatus(teamdto.getStatus());
		team.setTeamDescription(teamdto.getTeamDescription());
		team.setType(teamdto.getType());
		team.setCountry(country);			
		teamRepository.save(team);
	}
	public void insertPlayer(PlayerDto playerDto) {		
		User userObj=playerAdd(playerDto);
		
		Set<User> members = new HashSet<>();
		members.add(userObj);
		
		Team team = teamRepository.findById(playerDto.getTeamId()).get();
		team.setMembers(members);
		teamRepository.save(team);		
	}
	private User playerAdd(PlayerDto playerDto) {
		var user=userRepository.findByUsername(playerDto.getUsername());
		if (!user.isEmpty()) {
			throw new ResourceAlreadyExistsException("User name already existed.");
		}
		User userObj = new User();
		userObj.setAge(playerDto.getAge());
		userObj.setDOB(playerDto.getDob());
		userObj.setLogo(playerDto.getLogo());
		userObj.setName(playerDto.getName());
		userObj.setPassword(passwordEncoder.encode("secret"));
		
		Set<Role> roles = new HashSet<>();
		roles.add(authorityService.findByRoleName("PLAYER"));
		userObj.setRoles(roles);
		
		userObj.setUsername(playerDto.getUsername());					
		userRepository.save(userObj);
		return userObj;
	}
	
	private void checkTeamInDb(TeamDto c) {
		List<Team> team = teamRepository.findByName(c.getName());
		if (!team.isEmpty()) {
			throw new ResourceAlreadyExistsException("Team Already exists");
		}
	}
	public void deleteById(long id) {			
		teamRepository.deleteById(id);
	}
	public void update(TeamDto teamDto) {
		Team team = teamRepository.findById(teamDto.getId()).get();
		Country country=countryRepository.findByCountryId(teamDto.getCountryId());		
		 if (country == null) { throw new
		 ResourceAlreadyExistsException("Invalid Country."); }		
		team.setName(teamDto.getName());
		team.setCountry(country);
		team.setTeamLogo(teamDto.getLogo());	
		team.setTeamDescription(teamDto.getTeamDescription());	
		team.setStatus(teamDto.getStatus());		
		team.setType(teamDto.getType());
		teamRepository.save(team);
	}

	public Team getTeamByTeamId(long id) {
		Team team = teamRepository.findById(id).get();
		var members=team.getMembers();
		return team;
	}

	public Page<Team> getAllTeams(String searchText,int pageIndex,int rows,String sort) {
		Pageable pageWithElements;
		if(sort.equals("NA")) {			
			pageWithElements = PageRequest.of(pageIndex, rows,Sort.by("name").ascending());
		}else {			
			pageWithElements = PageRequest.of(pageIndex, rows,Sort.by("name").descending());	
		}		
		Page<Team> teams=teamRepository.findByNameContaining(searchText,pageWithElements);		
		return teams;
	}
}
