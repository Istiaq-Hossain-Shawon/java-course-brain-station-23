package com.icc.application.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icc.applicaiton.enums.Role;
import com.icc.application.dto.Captain;
import com.icc.application.dto.Player;
import com.icc.application.dto.UserDto;
import com.icc.application.model.User;
import com.icc.application.service.CaptainService;
import com.icc.application.service.CoachService;
import com.icc.application.service.PlayerService;

@Controller
public class RegisterController {

	@Autowired
	CaptainService captainService;
	@Autowired
	PlayerService playerService;
	@Autowired
	CoachService coachService;

	@GetMapping("/register/index")
	public String getAddCaptainPage(Model model) {
		model.addAttribute("pageTitle", "register");
		model.addAttribute("userDto", new UserDto());
		model.addAttribute("message", "Register new user");
		return "register/index";
	}

	@PostMapping("/register/add")
	public ResponseEntity<String> register(Model model, @ModelAttribute(name = "user") UserDto user) {
		
		if (user.getName()=="") {
	        return ResponseEntity.badRequest()
	            .body("Please give name...");
	    }
		if (user.getPassword()=="") {
	        return ResponseEntity.badRequest()
	            .body("Please give password...");
	    }
		if (user.getPassword()!=user.getConfirmPassword()) {
	        return ResponseEntity.badRequest()
	            .body("password does not match with confirm password...");
	    }
		
		if (user.getUsername()=="") {
	        return ResponseEntity.badRequest()
	            .body("Please give user name...");
	    }
		if (user.getRole()==null) {
	        return ResponseEntity.badRequest()
	            .body("Please give role...");
	    }
		
		
		if(user.getRole()==Role.ROLE_COACH){
			Captain captain = new Captain();
			captain.setAge(user.getAge());
			captain.setDOB(user.getDOB());
			captain.setName(user.getName());
			captain.setPassword(user.getPassword());
			captain.setRole(Role.ROLE_COACH);
			captain.setUsername(user.getUsername());			
			captainService.insert(captain);
		}
		if(user.getRole()==Role.ROLE_PLAYER){
			Player player = new Player();
			player.setAge(user.getAge());
			player.setDOB(user.getDOB());
			player.setName(user.getName());
			player.setPassword(user.getPassword());
			player.setRole(Role.ROLE_COACH);
			player.setUsername(user.getUsername());			
			playerService.insert(player);
		}	
		
		model.addAttribute("message", "register successfully");
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Success");
	}

	
}
