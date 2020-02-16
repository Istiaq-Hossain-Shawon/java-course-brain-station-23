package com.icc.application.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.icc.application.dto.TeamManager;
import com.icc.application.service.TeamManagerService;

@Controller
public class TeamManagerController {

	@Autowired
	TeamManagerService teamManagerService;

	@GetMapping("/teamManager/add")
	public String getAddTeamManagerPage(Model model) {
		model.addAttribute("pageTitle", "Add teamManager");
		model.addAttribute("teamManager", new TeamManager());
		model.addAttribute("message", "Add a new teamManager");
		return "teamManager/add";
	}

	@PostMapping("/teamManager/add")
	public String addTeamManager(Model model, @ModelAttribute(name = "teamManager") TeamManager teamManager) {
		teamManagerService.insert(teamManager);
		model.addAttribute("message", "TeamManager added successfully");
		return "redirect:/teamManager/show-all";
	}

	@GetMapping("/teamManager/show-all")
	public String showAllTeamManager(Model model) {
		model.addAttribute("pageTitle", "teamManager List");
		model.addAttribute("countries", teamManagerService.getAll());
		model.addAttribute("message", "Showing all teamManager...");
		return "/teamManager/show-all";
	}

	@GetMapping("/teamManager/teamManagers")
	public String teamManagersPage(Model model) {
		model.addAttribute("teamManager_list", teamManagerService.getAll());
		model.addAttribute("teamManager", new TeamManager());
		model.addAttribute("message", "Showing all teamManager...");
		return "teamManager/teamManagers";
	}

	@GetMapping("/teamManager/edit")
	public String editTeamManager(Model model, @RequestParam("id") long id) {
		model.addAttribute("teamManager", teamManagerService.get(id));
		return "teamManager/edit";
	}

	@PostMapping("/teamManager/edit")
	public String saveEditedTeamManager(Model model, @ModelAttribute(name = "teamManager") TeamManager teamManager) {
		teamManagerService.update(teamManager);
		model.addAttribute("message", "TeamManager saved successfully");
		return "redirect:/teamManager/teamManagers";
	}

	@GetMapping("/teamManager/delete")
	public String deleteTeamManagerByTeamManagerCode(Model model, @RequestParam("id") long id) {

		teamManagerService.delete(id);
		model.addAttribute("message", "teamManager deleted successfully");
		return "redirect:/teamManager/teamManagers";
	}

	@PostMapping("/teamManager/search")
	public String searchTeamManagerByTeamManagerCode(Model model, @ModelAttribute(name = "teamManager") TeamManager teamManager) {

		model.addAttribute("course_list", teamManagerService.getAll());
		return "teamManager/teamManagers";
	}
	
}
