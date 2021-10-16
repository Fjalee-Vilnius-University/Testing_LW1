package com.prekes.web.prekesweb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prekes.web.prekesweb.model.Preke;

public interface PrekeRepository extends CrudRepository<Preke, Integer> {
	
	List<Preke> findByPavadinimas(String pavadinimas);
	
	List<Preke> findByPavadinimasAndSalis(String pavadinimas, String salis);
	
	List<Preke> findDistinctByPavadinimas(String pavadinimas);
	
	Preke findOneByPavadinimas(String pavadinimas);
}
