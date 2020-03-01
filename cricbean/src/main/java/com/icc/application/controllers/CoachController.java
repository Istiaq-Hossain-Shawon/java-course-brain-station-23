package com.icc.application.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.icc.application.dto.Coach;
import com.icc.application.service.CoachService;

@Controller
public class CoachController {

	@Autowired
	CoachService coachService;

	@GetMapping("/coach/add")
	public String getAddCoachPage(Model model) {
		model.addAttribute("pageTitle", "Add coach");
		model.addAttribute("coach", new Coach());
		model.addAttribute("message", "Add a new coach");
		return "coach/add";
	}

	@PostMapping("/coach/add")
	public String addCoach(Model model, @ModelAttribute(name = "coach") Coach coach) {
		coachService.insert(coach);
		model.addAttribute("message", "Coach added successfully");
		return "redirect:/coach/show-all";
	}

	@GetMapping("/coach/show-all")
	public String showAllCoach(Model model) {
		model.addAttribute("pageTitle", "coach List");
		model.addAttribute("coachs", coachService.getAll());
		model.addAttribute("message", "Showing all coach...");
		return "/coach/show-all";
	}

	@GetMapping("/coach/coachs")
	public String coachsPage(Model model) {
		model.addAttribute("coach_list", coachService.getAll());
		model.addAttribute("coach", new Coach());
		model.addAttribute("message", "Showing all coach...");
		return "coach/coachs";
	}

	@GetMapping("/coach/edit")
	public String editCoach(Model model, @RequestParam("id") long id) {
		model.addAttribute("coach", coachService.get(id));
		return "coach/edit";
	}

	@PostMapping("/coach/edit")
	public String saveEditedCoach(Model model, @ModelAttribute(name = "coach") Coach coach) {
		coachService.update(coach);
		model.addAttribute("message", "Coach saved successfully");
		return "redirect:/coach/coachs";
	}

	@GetMapping("/coach/delete")
	public String deleteCoachByCoachCode(Model model, @RequestParam("id") long id) {

		coachService.delete(id);
		model.addAttribute("message", "coach deleted successfully");
		return "redirect:/coach/coachs";
	}

	@PostMapping("/coach/search")
	public String searchCoachByCoachCode(Model model, @ModelAttribute(name = "coach") Coach coach) {

		model.addAttribute("coach_list", coachService.getAll());
		return "coach/coachs";
	}
	
}
