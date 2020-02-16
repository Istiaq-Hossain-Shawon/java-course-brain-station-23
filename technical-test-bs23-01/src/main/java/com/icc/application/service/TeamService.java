package com.icc.application.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.application.exceptions.ResourceAlreadyExistsException;
import com.icc.application.model.Country;
import com.icc.application.model.Team;
import com.icc.application.dto.TeamDto;
import com.icc.application.repositories.CountryRepository;
import com.icc.application.repositories.TeamRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private CountryRepository countryRepository;
	

	public void insert(TeamDto teamdto) {
		checkTeamInDb(teamdto);
		Country country=countryRepository.findByCountryId(teamdto.getId());
		if (country == null) {
			throw new ResourceAlreadyExistsException("Invalid Country.");
		}
		Team team = new Team();
		team.setName(teamdto.getName());
		team.setCountry(country);			
		teamRepository.save(team);
	}

	private void checkTeamInDb(TeamDto c) {
		Team team = teamRepository.findByName(c.getName()).get(0);
		if (team != null) {
			throw new ResourceAlreadyExistsException("Team Already exists");
		}
	}
	public void deleteById(long id) {			
		teamRepository.deleteById(id);
	}
	public void update(TeamDto teamDto) {
		Team team = teamRepository.findById(teamDto.getId()).get();
		Country country=countryRepository.findByCountryId(teamDto.getId());
		if (country == null) {
			throw new ResourceAlreadyExistsException("Invalid Country.");
		}
		team.setName(teamDto.getName());
		team.setCountry(country);	
		teamRepository.save(team);
	}

	public Team getTeamByTeamId(long id) {
		Team team = teamRepository.findById(id).get();
		return team;
	}

	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}
}
