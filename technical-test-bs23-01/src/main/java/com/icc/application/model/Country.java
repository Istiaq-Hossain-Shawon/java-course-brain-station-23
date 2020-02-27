package com.icc.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "tbl_country")
public class Country implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long countryId;
	@Column(name = "country_name", nullable = false)
	private String countryName;
	
	@Column(name = "logo", nullable = false)
	private String logo;
	
	public long getCountryId() {
		return countryId;
	}
	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Country() {
		super();
	}
	public Country(long id,  String countryName) {
		super();
		this.countryId = id;		
		this.countryName = countryName;
	}
	public long getId() {
		return countryId;
	}
	public void setId(long id) {
		this.countryId = id;
	}
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
