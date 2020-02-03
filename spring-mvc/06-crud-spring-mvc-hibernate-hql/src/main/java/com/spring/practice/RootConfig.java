package com.spring.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.spring.practice.config.HibernateConfig;

@ComponentScan(basePackages = { "com.spring.practice.service", "com.spring.practice.config" })
public class RootConfig {
	@Bean
	GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}
	@Bean
	HibernateConfig hibernateConfig() {
		return new HibernateConfig();
	}
}
