package com.icc.application.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icc.application.model.Team;
import com.icc.application.service.TeamService;

@Controller
public class TeamController {

	@Autowired
	TeamService teamService;

	@GetMapping("/team/add")
	public String getAddTeamPage(Model model) {
		model.addAttribute("pageTitle", "Add Team");
		model.addAttribute("team", new Team());
		model.addAttribute("message", "Add a new Team");
		return "team/add";

	}

	@PostMapping("/team/add")
	public String addTeam(Model model, @ModelAttribute(name = "team") Team team) {
		teamService.add(team);
		model.addAttribute("message", "Team added successfully");

		return "redirect:/team/show-all";

	}

	@GetMapping("/team/show-all")
	public String showAllTeam(Model model) {
		model.addAttribute("pageTitle", "Team List");
		model.addAttribute("teams", teamService.getAllTeams());
		model.addAttribute("message", "Showing all team...");

		return "/team/show-all";
	}

	@GetMapping("/team/teams")
	public String teamsPage(Model model) {

		model.addAttribute("team_list", teamService.getAllTeams());
		model.addAttribute("team", new Team());
		model.addAttribute("message", "Showing all team...");

		return "team/teams";

	}

	@GetMapping("/team/edit")
	public String editTeamByTeamCode(Model model, @RequestParam("id") Long id) {

		model.addAttribute("team", teamService.getTeamByTeamId(id));
		// model.addAttribute("team", new Team());

		return "team/edit";
	}

	@PostMapping("/team/edit")
	public String saveEditedTeam(Model model, @ModelAttribute(name = "team") Team team) {
		teamService.saveEditedTeam(team);
		model.addAttribute("message", "Team saved successfully");

		return "redirect:/team/teams";
	}

	@GetMapping("/team/delete")
	public String deleteTeamByTeamCode(Model model, @RequestParam("id") long id) {		
		teamService.deleteById(id);
		model.addAttribute("message", "Team deleted successfully");

		return "redirect:/team/teams";
	}

	@PostMapping("/team/search")
	public String searchTeamByTeamCode(Model model, @ModelAttribute(name = "team") Team team) {

		var teamList = new ArrayList();
		teamList.add(teamService.getTeamByTeamId(team.getId()));
		model.addAttribute("team_list", teamList);

		return "team/teams";
	}

}
