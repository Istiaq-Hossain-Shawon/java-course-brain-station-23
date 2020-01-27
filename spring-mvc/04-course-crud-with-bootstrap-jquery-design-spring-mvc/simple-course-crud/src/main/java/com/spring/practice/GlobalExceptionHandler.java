package com.spring.practice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.CONFLICT) // 409
	@ExceptionHandler(RuntimeException.class)
	public String handleConflict(HttpServletRequest req, Exception e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "error";
	}
	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(NullPointerException.class)
	public String handleIOException(HttpServletRequest req, Exception e, Model model) {
		model.addAttribute("message", "IOException handler executed." + e.getMessage());
		return "error";
	}	
    @ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)	  
    @ExceptionHandler(InternalServerError.class) public String
	handleInternalServerErro(HttpServletRequest req, Exception e, Model model){
	  model.addAttribute("message", "INTERNAL SERVER ERROR."+e.getMessage());
	  return "error"; 
	}	  
	
	@ResponseStatus(code=HttpStatus.NOT_FOUND)	  
	@ExceptionHandler(NotFound.class) public String
	handleNotFound(HttpServletRequest req, Exception e, Model model){
		  model.addAttribute("message", "Not Found."+e.getMessage());
		  return "error"; 
	}
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)	  
	@ExceptionHandler(NoHandlerFoundException.class) public String
	handleNoHandlerFoundException(HttpServletRequest req, NoHandlerFoundException  e, Model model){
		  model.addAttribute("message", "Not Found Route."+e.getMessage());
		  return "error"; 
	}
}
