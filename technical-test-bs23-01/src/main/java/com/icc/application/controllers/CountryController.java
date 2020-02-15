package com.icc.application.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.icc.application.model.Country;
import com.icc.application.service.CountryService;


@Controller
public class CountryController {

	@Autowired
	CountryService countryService;

	@GetMapping("/country/add")
	public String getAddCountryPage(Model model) {
		model.addAttribute("pageTitle", "Add country");
		model.addAttribute("country", new Country());
		model.addAttribute("message", "Add a new country");
		return "country/add";
	}

	@PostMapping("/country/add")
	public String addCountry(Model model, @ModelAttribute(name = "country") Country country) {
		countryService.addCountry(country);
		model.addAttribute("message", "Country added successfully");
		return "redirect:/country/show-all";

	}

	@GetMapping("/country/show-all")
	public String showAllCountry(Model model) {
		model.addAttribute("pageTitle", "country List");
		model.addAttribute("countries", countryService.getAllCounties());
		model.addAttribute("message", "Showing all country...");

		return "/country/show-all";
	}

	@GetMapping("/country/countries")
	public String coursesPage(Model model) {

		model.addAttribute("country_list", countryService.getAllCounties());
		model.addAttribute("country", new Country());
		model.addAttribute("message", "Showing all country...");
		return "country/countries";

	}

	@GetMapping("/country/edit")
	public String editCountryByCountryCode(Model model, @RequestParam("id") long id) {
		model.addAttribute("country", countryService.getCountryByCountryId(id));
		return "country/edit";
	}

	@PostMapping("/country/edit")
	public String saveEditedCountry(Model model, @ModelAttribute(name = "country") Country country) {
		countryService.saveEditedCountry(country);
		model.addAttribute("message", "Country saved successfully");
		return "redirect:/country/countries";
	}

	@GetMapping("/country/delete")
	public String deleteCountryByCountryCode(Model model, @RequestParam("id") long id) {

		countryService.deleteById(id);
		model.addAttribute("message", "country deleted successfully");

		return "redirect:/country/countries";
	}

	@PostMapping("/country/search")
	public String searchCountryByCountryCode(Model model, @ModelAttribute(name = "country") Country country) {
		var courseList = new ArrayList();
		courseList.add(countryService.getCountryByCountryName(country.getCountryName()));
		model.addAttribute("course_list", courseList);
		return "course/courses";
	}

}
