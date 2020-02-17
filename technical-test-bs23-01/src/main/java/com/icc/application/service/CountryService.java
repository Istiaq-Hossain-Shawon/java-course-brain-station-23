package com.icc.application.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icc.application.model.Country;
import com.icc.application.repositories.CountryRepository;
import com.icc.application.exceptions.ResourceAlreadyExistsException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public void addCountry(Country country) {
		checkCountryInDb(country);		
		countryRepository.save(country);
	}
	private void checkCountryInDb(Country c) {
		List<Country> country = countryRepository.findByCountryName(c.getCountryName());
		if (!country.isEmpty() ) {
			throw new ResourceAlreadyExistsException("Country Already exists");
		}
	}
	public void saveEditedCountry(Country c) {

		Country country = countryRepository.findByCountryId(c.getId());
		//BeanUtils.copyProperties(c, country);
		country.setCountryName(c.getCountryName());
		countryRepository.save(country);
	}
	public void deleteById(long id) {			
		countryRepository.deleteById(id);
	}
	

	public Country getCountryByCountryId(long id) {
		Country country = countryRepository.findByCountryId(id);
		return country;
	}

	public List<Country> getAllCounties() {
		List<Country> countris=countryRepository.findAll();
		return countris;
	}
	public List<Country> getCountryByCountryName(String countryName ) {
		return countryRepository.findByCountryName(countryName);
	}
	
	
	
}
