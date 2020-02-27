package com.icc.application.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icc.application.dto.CountryDto;
import com.icc.application.dto.TeamDto;
import com.icc.application.model.Country;
import com.icc.application.service.CountryService;
import com.icc.application.util.Constants;


@Controller
public class CountryController {

	@Autowired
	CountryService countryService;
	@Autowired	ServletContext context;
	@GetMapping("/country/add")
	public String getAddCountryPage(Model model) {
		model.addAttribute("pageTitle", "Add country");
		model.addAttribute("country", new Country());
		model.addAttribute("message", "Add a new country");
		return "country/add";
	}

	@PostMapping("/country/add")
	public String addCountry(@ModelAttribute(name = "country") Country country,@RequestParam("file") MultipartFile file,Model model) {
		
		if(country.getCountryName() == null || country.getCountryName().trim().isEmpty()) {
			throw new RuntimeException("Please give team name");
		}
				
		if (file.isEmpty()) {
			 throw new RuntimeException("Please select a file to upload");
		}
		 try {
			 byte[] bytes = file.getBytes();
			 String absoluteFilePath = context.getRealPath(Constants.UPLOADED_FOLDER);
			 Path path = Paths.get(absoluteFilePath + file.getOriginalFilename());
	         Files.write(path, bytes);
	         country.setLogo(file.getOriginalFilename());
	         countryService.addCountry(country);
	 		model.addAttribute("message", "Country added successfully");
	 		return "redirect:/country/show-all";
	    }catch (IOException e) {
	        	throw new RuntimeException(e.getMessage());
	    }	

	}

	@GetMapping("/country/show-all")
	public String showAllCountry(Model model) {
		model.addAttribute("pageTitle", "country List");
		model.addAttribute("countries", countryService.getAllCounties());
		model.addAttribute("message", "Showing all country...");
		model.addAttribute("country", new Country());
		model.addAttribute("pageIndex","1");
		return "/country/show-all";
	}

	@GetMapping("/country/countries")
	public String coursesPage(Model model) {
		model.addAttribute("country_list", countryService.getAllCounties());		
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
	@GetMapping("/country/detail")
	public String detail(Model model, @RequestParam("id") long id) {
		model.addAttribute("country", countryService.getCountryByCountryId(id));
		return "country/detail";
	}
	
	@PostMapping("/country/search")
	public String searchCountryByCountryCode(Model model, @ModelAttribute(name = "country") CountryDto country,@RequestParam("pageIndex") String pageIndex) {
		model.addAttribute("course_list", countryService.getCountryByCountryName(country));		
		model.addAttribute("pageIndex",pageIndex);
		return "country/countries";
	}

}
