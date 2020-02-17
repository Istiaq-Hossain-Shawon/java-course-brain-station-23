package com.icc.application.controllers;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.icc.application.dto.TeamDto;
import com.icc.application.model.Country;
import com.icc.application.model.Team;
import com.icc.application.service.CountryService;
import com.icc.application.service.TeamService;

import antlr.collections.List;

@Controller
public class TeamController {

	@Autowired
	TeamService teamService;
	@Autowired
	CountryService countryService;

	@GetMapping("/team/add")
	public String getAddTeamPage(Model model) {
		model.addAttribute("pageTitle", "Add team");
		model.addAttribute("team", new TeamDto());
		var cuntries= countryService.getAllCounties();
		 Map< Long, String > Countries = new HashMap<Long, String>();	          
	        for(Country item : cuntries){
	        	Countries.put(item.getId(), item.getCountryName());	
	    	}		
		model.addAttribute("countryList", Countries);		
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
		return "team/show-all";
	}

	@GetMapping("/team/teams")
	public String teamsPage(Model model) {
		model.addAttribute("teams", teamService.getAllTeams());
		model.addAttribute("team", new TeamDto());
		model.addAttribute("message", "Showing all team...");
		return "team/teams";
	}

	@GetMapping("/team/edit")
	public String editTeam(Model model, @RequestParam("id") long id) {
		var cuntries= countryService.getAllCounties();
		 Map< Long, String > Countries = new HashMap<Long, String>();	          
	        for(Country item : cuntries){
	        	Countries.put(item.getId(), item.getCountryName());	
	    	}		
		model.addAttribute("countryList", Countries);	
		Team team = teamService.getTeamByTeamId(id);
		TeamDto teamdto= new TeamDto();
		teamdto.setId(team.getId());
		teamdto.setCountry(team.getCountry());
		teamdto.setCountryId(team.getCountry().getId());
		teamdto.setName(team.getName());
		model.addAttribute("team", teamdto);				
		return "team/edit";
	}

	@PostMapping("/team/editTeam")
	public String saveEditedTeam(Model model, @ModelAttribute(name = "team") TeamDto team) {
		teamService.update(team);
		model.addAttribute("message", "Team saved successfully");
		return "redirect:/team/show-all";
	}
	
	@GetMapping("/team/detail")
	public String detailTeam(Model model, @RequestParam("id") long id) {
		model.addAttribute("team", teamService.getTeamByTeamId(id));
		model.addAttribute("countryList", countryService.getAllCounties());		
		return "team/detail";
	}
	
	@GetMapping("/team/delete")
	public String deleteTeamByTeamId(Model model, @RequestParam("id") long id) {
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
