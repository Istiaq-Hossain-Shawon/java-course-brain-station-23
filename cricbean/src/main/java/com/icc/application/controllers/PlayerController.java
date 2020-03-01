package com.icc.application.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.icc.application.dto.PlayerDto;
import com.icc.application.service.PlayerService;

@Controller
public class PlayerController {

	@Autowired
	PlayerService playerService;

	@GetMapping("/player/add")
	public String getAddPlayerPage(Model model) {
		model.addAttribute("pageTitle", "Add player");
		model.addAttribute("player", new PlayerDto());
		model.addAttribute("message", "Add a new player");
		return "player/add";
	}

	@PostMapping("/player/add")
	public String addPlayer(Model model, @ModelAttribute(name = "player") PlayerDto player) {
		playerService.insert(player);
		model.addAttribute("message", "Player added successfully");
		return "redirect:/player/show-all";
	}

	@GetMapping("/player/show-all")
	public String showAllPlayer(Model model) {
		model.addAttribute("pageTitle", "player List");
		model.addAttribute("players", playerService.getAll());
		model.addAttribute("message", "Showing all player...");
		return "/player/show-all";
	}

	@GetMapping("/player/players")
	public String playersPage(Model model) {
		model.addAttribute("player_list", playerService.getAll());
		model.addAttribute("player", new PlayerDto());
		model.addAttribute("message", "Showing all player...");
		return "player/players";
	}

	@GetMapping("/player/edit")
	public String editPlayer(Model model, @RequestParam("id") long id) {
		model.addAttribute("player", playerService.get(id));
		return "player/edit";
	}

	@PostMapping("/player/edit")
	public String saveEditedPlayer(Model model, @ModelAttribute(name = "player") PlayerDto player) {
		playerService.update(player);
		model.addAttribute("message", "Player saved successfully");
		return "redirect:/player/players";
	}

	@GetMapping("/player/delete")
	public String deletePlayerByPlayerCode(Model model, @RequestParam("id") long id) {

		playerService.delete(id);
		model.addAttribute("message", "player deleted successfully");
		return "redirect:/player/players";
	}

	@PostMapping("/player/search")
	public String searchPlayerByPlayerCode(Model model, @ModelAttribute(name = "player") PlayerDto player) {

		model.addAttribute("player_list", playerService.getAll());
		return "player/players";
	}
	
}
