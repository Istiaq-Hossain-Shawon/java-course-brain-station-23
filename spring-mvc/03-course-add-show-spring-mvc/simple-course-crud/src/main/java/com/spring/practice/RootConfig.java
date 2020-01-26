package com.spring.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.spring.practice.service" })
public class RootConfig {
	@Bean
	GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}
}
