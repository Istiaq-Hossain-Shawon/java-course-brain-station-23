package com.icc.application.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.icc.application.dto.IccEmployee;
import com.icc.application.service.IccEmployeeService;

@Controller
public class IccEmployeeController {

	@Autowired
	IccEmployeeService iccEmployeeService;

	@GetMapping("/iccEmployee/add")
	public String getAddIccEmployeePage(Model model) {
		model.addAttribute("pageTitle", "Add iccEmployee");
		model.addAttribute("iccEmployee", new IccEmployee());
		model.addAttribute("message", "Add a new iccEmployee");
		return "iccEmployee/add";
	}

	@PostMapping("/iccEmployee/add")
	public String addIccEmployee(Model model, @ModelAttribute(name = "iccEmployee") IccEmployee iccEmployee) {
		iccEmployeeService.insert(iccEmployee);
		model.addAttribute("message", "IccEmployee added successfully");
		return "redirect:/iccEmployee/show-all";
	}

	@GetMapping("/iccEmployee/show-all")
	public String showAllIccEmployee(Model model) {
		model.addAttribute("pageTitle", "iccEmployee List");
		model.addAttribute("iccEmployees", iccEmployeeService.getAll());
		model.addAttribute("message", "Showing all iccEmployee...");
		return "/iccEmployee/show-all";
	}

	@GetMapping("/iccEmployee/iccEmployees")
	public String iccEmployeesPage(Model model) {
		model.addAttribute("iccEmployee_list", iccEmployeeService.getAll());
		model.addAttribute("iccEmployee", new IccEmployee());
		model.addAttribute("message", "Showing all iccEmployee...");
		return "iccEmployee/iccEmployees";
	}

	@GetMapping("/iccEmployee/edit")
	public String editIccEmployee(Model model, @RequestParam("id") long id) {
		model.addAttribute("iccEmployee", iccEmployeeService.get(id));
		return "iccEmployee/edit";
	}

	@PostMapping("/iccEmployee/edit")
	public String saveEditedIccEmployee(Model model, @ModelAttribute(name = "iccEmployee") IccEmployee iccEmployee) {
		iccEmployeeService.update(iccEmployee);
		model.addAttribute("message", "IccEmployee saved successfully");
		return "redirect:/iccEmployee/iccEmployees";
	}

	@GetMapping("/iccEmployee/delete")
	public String deleteIccEmployeeByIccEmployeeCode(Model model, @RequestParam("id") long id) {

		iccEmployeeService.delete(id);
		model.addAttribute("message", "iccEmployee deleted successfully");
		return "redirect:/iccEmployee/iccEmployees";
	}

	@PostMapping("/iccEmployee/search")
	public String searchIccEmployeeByIccEmployeeCode(Model model, @ModelAttribute(name = "iccEmployee") IccEmployee iccEmployee) {

		model.addAttribute("iccEmployee_list", iccEmployeeService.getAll());
		return "iccEmployee/iccEmployees";
	}
	
	
	
}
