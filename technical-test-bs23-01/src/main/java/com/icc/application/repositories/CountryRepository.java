package com.icc.application.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.icc.application.model.Country;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CountryRepository extends  PagingAndSortingRepository<Country, Long>{
	
	Country findByCountryId(Long id);
	
	List<Country> findByCountryName(String CountryName,Pageable pageable);
	
	Page<Country> findByCountryNameContaining(String CountryName,Pageable pageable);	

}


