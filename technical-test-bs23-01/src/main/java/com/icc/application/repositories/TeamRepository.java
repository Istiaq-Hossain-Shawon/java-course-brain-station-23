package com.icc.application.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.icc.application.model.Country;
import com.icc.application.model.Team;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TeamRepository extends JpaRepository<Team, Long>{
	
	Optional<Team> findById(Long id);
	
	List<Team> findByName(String teamName);
	
	//List<Team> findByCountry_CountryId(long id);
	
	Page<Team> findByNameContaining(String Name,Pageable pageable);	
	
}
