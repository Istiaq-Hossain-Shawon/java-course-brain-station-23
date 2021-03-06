package com.icc.application.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder passwordEncoder;

	private final UserDetailsService userDetailsService;

	@Autowired
	public WebSecurityConfiguration(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        // We are disabling CSRF so that our forms don't complain for a CSRF token.
        // Beware that it can create a security vulnerability
		http.csrf().disable();

        // We are permitting all static resources to be accessed publicly
		http
			.authorizeRequests()
			.antMatchers("/img/**", "/css/**", "/js/**","/fonts/**").permitAll()
                // We are restricting endpoints for individual roles.
                // Only users with allowed roles will be able to access individual endpoints.
		.and()
			.authorizeRequests()
			.antMatchers("/iccEmployee/add").hasRole("ADMIN")
			.antMatchers("/iccEmployee/show-all").hasAnyRole("ADMIN", "ICC_EMPLOYEE")
			.antMatchers("/iccEmployee/edit").hasAnyRole("ICC_EMPLOYEE")
			
			.antMatchers("/country/**").hasRole("ICC_EMPLOYEE")
					
			
			.antMatchers("/teamManager/add").hasRole("ICC_EMPLOYEE")
			.antMatchers("/teamManager/show-all").hasAnyRole("ICC_EMPLOYEE","TEAM_MANAGER")
			.antMatchers("/teamManager/edit").hasAnyRole("TEAM_MANAGER")			
			
			.antMatchers("/team/add").hasAnyRole("TEAM_MANAGER","ICC_EMPLOYEE")
			.antMatchers("/team/show-all").hasAnyRole("TEAM_MANAGER","ICC_EMPLOYEE","CAPTAIN","PLAYER")
			.antMatchers("/team/edit").hasAnyRole("TEAM_MANAGER","ICC_EMPLOYEE")			
			.antMatchers("/team/detail").hasAnyRole("ICC_EMPLOYEE","TEAM_MANAGER","CAPTAIN","PLAYER")
			
			.antMatchers("/captain/add").hasAnyRole("TEAM_MANAGER","ICC_EMPLOYEE")
			.antMatchers("/captain/show-all").hasAnyRole("TEAM_MANAGER","CAPTAIN","PLAYER","ICC_EMPLOYEE")
			.antMatchers("/captain/edit").hasAnyRole("TEAM_MANAGER")			
			
			.antMatchers("/player/add").hasAnyRole("TEAM_MANAGER","ICC_EMPLOYEE")
			.antMatchers("/player/show-all").hasAnyRole("TEAM_MANAGER","PLAYER","CAPTAIN")
			.antMatchers("/player/edit").hasAnyRole("PLAYER","ICC_EMPLOYEE")			
			
			.antMatchers("/coach/add").hasRole("TEAM_MANAGER")
			.antMatchers("/coach/show-all").hasAnyRole("TEAM_MANAGER","COACH")
			.antMatchers("/coach/edit").hasAnyRole("COACH")		
						
			.antMatchers("/register/index").permitAll()
			.antMatchers("/register/add").permitAll()
			
			
			
                // Following line denotes that all requests must be authenticated.
                // Hence, once a request comes to our application, we will check if the user is authenticated or not.
			.anyRequest().authenticated()

                // Here we are configuring our login form
		.and()
			.formLogin()
                .loginPage("/login") // Login page will be accessed through this endpoint. We will create a controller method for this.
                .loginProcessingUrl("/login-processing") // This endpoint will be mapped internally. This URL will be our Login form post action.
                .permitAll() // We re permitting all for login page
			.usernameParameter("username")
			.passwordParameter("password")
                .defaultSuccessUrl("/") // If the login is successful, user will be redirected to this URL.
                .failureUrl("/login?error=true") // If the user fails to login, application will redirect the user to this endpoint
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
	}
}
