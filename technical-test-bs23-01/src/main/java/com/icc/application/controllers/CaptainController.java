package com.icc.application.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.icc.application.dto.Captain;
import com.icc.application.service.CaptainService;

@Controller
public class CaptainController {

	@Autowired
	CaptainService captainService;

	@GetMapping("/captain/add")
	public String getAddCaptainPage(Model model) {
		model.addAttribute("pageTitle", "Add captain");
		model.addAttribute("captain", new Captain());
		model.addAttribute("message", "Add a new captain");
		return "captain/add";
	}

	@PostMapping("/captain/add")
	public String addCaptain(Model model, @ModelAttribute(name = "captain") Captain captain) {
		captainService.insert(captain);
		model.addAttribute("message", "Captain added successfully");
		return "redirect:/captain/show-all";
	}

	@GetMapping("/captain/show-all")
	public String showAllCaptain(Model model) {
		model.addAttribute("pageTitle", "captain List");
		model.addAttribute("captains", captainService.getAll());
		model.addAttribute("message", "Showing all captain...");
		return "/captain/show-all";
	}

	@GetMapping("/captain/captains")
	public String captainsPage(Model model) {
		model.addAttribute("captain_list", captainService.getAll());
		model.addAttribute("captain", new Captain());
		model.addAttribute("message", "Showing all captain...");
		return "captain/captains";
	}

	@GetMapping("/captain/edit")
	public String editCaptain(Model model, @RequestParam("id") long id) {
		model.addAttribute("captain", captainService.get(id));
		return "captain/edit";
	}

	@PostMapping("/captain/edit")
	public String saveEditedCaptain(Model model, @ModelAttribute(name = "captain") Captain captain) {
		captainService.update(captain);
		model.addAttribute("message", "Captain saved successfully");
		return "redirect:/captain/captains";
	}

	@GetMapping("/captain/delete")
	public String deleteCaptainByCaptainCode(Model model, @RequestParam("id") long id) {

		captainService.delete(id);
		model.addAttribute("message", "captain deleted successfully");
		return "redirect:/captain/captains";
	}

	@PostMapping("/captain/search")
	public String searchCaptainByCaptainCode(Model model, @ModelAttribute(name = "captain") Captain captain) {

		model.addAttribute("captain_list", captainService.getAll());
		return "captain/captains";
	}
	
}
