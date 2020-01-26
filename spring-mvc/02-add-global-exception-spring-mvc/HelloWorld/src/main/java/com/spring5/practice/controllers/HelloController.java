package com.spring5.practice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping("/index")
	public String hello(@RequestParam Integer  id) {
		
		if(id==null)
	    {
	        throw new NullPointerException("Id is null.");
	    }
		return "index";		
	}
}
