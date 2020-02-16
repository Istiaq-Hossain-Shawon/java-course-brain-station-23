package com.icc.application.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.icc.application.dto.TeamDto;
import com.icc.application.service.TeamService;

@Controller
public class TeamController {

	@Autowired
	TeamService teamService;

	@GetMapping("/team/add")
	public String getAddTeamPage(Model model) {
		model.addAttribute("pageTitle", "Add team");
		model.addAttribute("team", new TeamDto());
		model.addAttribute("message", "Add a new team");
		return "team/add";
	}

	@PostMapping("/team/add")
	public String addTeam(Model model, @ModelAttribute(name = "team") TeamDto team) {
		teamService.insert(team);
		model.addAttribute("message", "Team added successfully");
		return "redirect:/team/show-all";
	}

	@GetMapping("/team/show-all")
	public String showAllTeam(Model model) {
		model.addAttribute("pageTitle", "team List");
		model.addAttribute("teams", teamService.getAllTeams());
		model.addAttribute("message", "Showing all team...");
		return "/team/show-all";
	}

	@GetMapping("/team/teams")
	public String teamsPage(Model model) {
		model.addAttribute("team_list", teamService.getAllTeams());
		model.addAttribute("team", new TeamDto());
		model.addAttribute("message", "Showing all team...");
		return "team/teams";
	}

	@GetMapping("/team/edit")
	public String editTeam(Model model, @RequestParam("id") long id) {
		model.addAttribute("team", teamService.getTeamByTeamId(id));
		return "team/edit";
	}

	@PostMapping("/team/edit")
	public String saveEditedTeam(Model model, @ModelAttribute(name = "team") TeamDto team) {
		teamService.update(team);
		model.addAttribute("message", "Team saved successfully");
		return "redirect:/team/teams";
	}

	@GetMapping("/team/delete")
	public String deleteTeamByTeamCode(Model model, @RequestParam("id") long id) {

		teamService.deleteById(id);
		model.addAttribute("message", "team deleted successfully");
		return "redirect:/team/teams";
	}

	@PostMapping("/team/search")
	public String searchTeamByTeamCode(Model model, @ModelAttribute(name = "team") TeamDto team) {

		model.addAttribute("team_list", teamService.getAllTeams());
		return "team/teams";
	}
	
}
