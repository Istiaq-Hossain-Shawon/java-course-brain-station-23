package com.icc.application.dto;

import java.util.Date;
import java.util.Set;

import com.icc.application.model.Role;



public class TeamManager {
	private long id;
	private String username;
	private String name;
	private String password;
private Set<Role> roles;
	
    public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	private int age;
	private Date dob;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDOB() {
		return dob;
	}
	public void setDOB(Date dOB) {
		dob = dOB;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
