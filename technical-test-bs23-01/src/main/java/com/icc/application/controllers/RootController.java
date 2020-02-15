package com.icc.application.controllers;


import com.icc.applicaiton.enums.Role;
import com.icc.application.model.User;
import com.icc.application.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.stream.Stream;


@Controller
public class RootController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public RootController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model, @RequestParam(name="error", required = false) String error) {
		generateUsers();
		model.addAttribute("error", error);
		return "auth/login";
	}

	private void generateUsers() {

		if (userRepository.findByUsername("admin").isEmpty()) {
			var user = new User();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("secret"));
			user.setRole(Role.ROLE_ADMIN);
			userRepository.save(user);
		}

		if (userRepository.findByUsername("user").isEmpty()) {
			var user = new User();
			user.setUsername("user");
			user.setPassword(passwordEncoder.encode("secret"));
			user.setRole(Role.ROLE_TEAM_MANAGER);
			userRepository.save(user);
		}
	}
}
