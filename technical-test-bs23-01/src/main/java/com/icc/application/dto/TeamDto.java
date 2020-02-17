package com.icc.application.dto;


import java.util.Set;
import com.icc.application.model.Country;
import com.icc.application.model.User;

public class TeamDto   {

	
	private long id;
	
	private String name;
	
	private long countryId;
	
	private Country country;
	
	private Set<User> members;	
	
	
	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

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



	public TeamDto() {
	}

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
}
