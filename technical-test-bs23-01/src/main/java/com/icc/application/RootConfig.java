package com.icc.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@ComponentScan(basePackages = {
		"com.icc.applicaion.service",
		"com.icc.applicaion.config.persistence",
		"com.icc.applicaion.config.security",
})
//@ComponentScan(basePackageClasses = {StudentService.class})
public class RootConfig {
	@Bean
	GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
