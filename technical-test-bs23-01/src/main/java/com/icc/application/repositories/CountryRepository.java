package com.icc.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.icc.application.model.Country;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Country, Long>{
	
	Country findByCountryId(Long id);
	
	List<Country> findByCountryName(String CountryName);	

}
