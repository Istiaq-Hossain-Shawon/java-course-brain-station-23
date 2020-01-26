package com.spring5.practice;

import org.springframework.context.annotation.Bean;

import com.spring5.practice.GlobalExceptionHandler;

public class RootConfig {
	@Bean
	GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}
}
