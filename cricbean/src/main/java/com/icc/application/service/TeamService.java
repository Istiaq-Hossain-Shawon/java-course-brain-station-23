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
import com.icc.application.dto.TeamManagerDto;
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
		Team team = teamRepository.findById(playerDto.getTeamId()).get();
		var data=userRepository.findById(userObj.getId()).get();
		
		Set<User> members = new HashSet<>();
		members.add(data);
		
		
		team.getMembers().forEach((temp) -> {
			members.add(temp);

        });
		team.setMembers(members);
		//team.getMembers().add(data);	
		
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
		roles.add(authorityService.findByRoleName("ROLE_PLAYER"));
		userObj.setRoles(roles);
		
		userObj.setUsername(playerDto.getUsername());					
		userRepository.save(userObj);
		return userObj;
	}
	
	
	public void insertTeamManager(TeamManagerDto teamManagerDto) {	
		var user=userRepository.findById(teamManagerDto.getId()).get();
		Team team = teamRepository.findById(teamManagerDto.getTeamId()).get();
		team.getMembers().add(user);		
		teamRepository.save(team);		
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
	public void updateTeamMember(Team team) {
		Team teamObj = teamRepository.findById(team.getId()).get();
		teamObj.setMembers(team.getMembers());
		teamRepository.save(teamObj);
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
	public void updateCaptainInTeam(Long teamId,Long playerId) {
		var team= teamRepository.findById(teamId).get();		
		for (var element : team.getMembers()) {
			for (var role : element.getRoles()) {
			    if(role.getRoleName()=="ROLE_CAPTAIN") {
			    	throw new ResourceAlreadyExistsException("Captain already existed.");			    	
			    }
			}
		}		
		var user =userRepository.findById(playerId).get();
		var data=user.getRoles();
		var role=authorityService.findByRoleName("ROLE_CAPTAIN");
		data.add(role);
		user.setRoles(data);
		userRepository.save(user);	
	}
}
