package com.prekes.web.prekesweb.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.prekes.web.prekesweb.model.Preke;

//http://localhost:8080/prekesRestRepository
//http://localhost:8080/prekesRestRepository/1
//http://localhost:8080/prekesRestRepository/?size=3
//http://localhost:8080/prekesRestRepository/?page=1&size=2
//http://localhost:8080/prekesRestRepository/?sort=pavadinimas,desc

//@Param("role") and go to http://localhost:8080/prekesRestRepository/search/findByPavadinimas?pavadinimas=Pienas

@RepositoryRestResource(path="prekesRestRepository", collectionResourceRel="prekes")
public interface PrekeRestRepository extends PagingAndSortingRepository<Preke, Integer> {
	
	List<Preke> findByPavadinimas(@Param("pavadinimas") String pavadinimas);
}
