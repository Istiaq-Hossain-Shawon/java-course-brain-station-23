package com.icc.application.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.application.exceptions.ResourceAlreadyExistsException;
import com.icc.application.model.Team;
import com.icc.application.repositories.TeamRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	public void add(Team team) {
		checkTeamInDb(team);		
		teamRepository.save(team);
	}

	private void checkTeamInDb(Team c) {
		var team = teamRepository.findByName(c.getName());
		if (team != null) {
			throw new ResourceAlreadyExistsException("Team Already exists");
		}
	}
	public void deleteById(long id) {			
		teamRepository.deleteById(id);
	}
	public void saveEditedTeam(Team c) {

		var team = teamRepository.findById(c.getId());
		BeanUtils.copyProperties(c, team);		
		teamRepository.save(team.get());

	}

	public Team getTeamByTeamId(long id) {
		var team = teamRepository.findById(id);
		return team.get();
	}

	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}
}
