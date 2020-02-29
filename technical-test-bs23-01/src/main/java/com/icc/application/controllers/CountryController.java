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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	 		return "redirect:/country/show-all?_search=&_pageIndex=0&_rows=5&_sort=NA";
	    }catch (IOException e) {
	        	throw new RuntimeException(e.getMessage());
	    }	

	}

	@RequestMapping(value = "country/show-all",params= {"_search","_pageIndex","_rows","_sort"},  method = RequestMethod.GET)
	public String showAllCountry(Model model,
			@RequestParam(value = "_search") String searchText,
			@RequestParam(value = "_pageIndex") int pageIndex,
			@RequestParam(value = "_rows") int rows,
			@RequestParam(value = "_sort") String sort) {
		 
		 var countryPage=countryService.getAllCountries(searchText,pageIndex,rows,sort);		 
		model.addAttribute("pageTitle", "country List");
		model.addAttribute("countries",countryPage.getContent());
		model.addAttribute("message", "Showing all country...");
		model.addAttribute("country", new Country());
		model.addAttribute("totalPages",countryPage.getTotalPages());
		model.addAttribute("pageIndex",pageIndex);
		return "/country/show-all";
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
	
	

}
