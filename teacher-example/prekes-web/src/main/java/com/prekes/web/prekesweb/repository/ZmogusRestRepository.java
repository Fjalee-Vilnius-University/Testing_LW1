package com.prekes.web.prekesweb.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.prekes.web.prekesweb.model.Zmogus;

//http://localhost:8080/zmonesRestRepository
//http://localhost:8080/zmonesRestRepository/1
//http://localhost:8080/zmonesRestRepository/?size=3
//http://localhost:8080/zmonesRestRepository/?page=1&size=2
//http://localhost:8080/zmonesRestRepository/?sort=vardas,desc

//@Param("role") and go to http://localhost:8080/zmonesRestRepository/search/findByRole?role=Admin

@RepositoryRestResource(path="zmonesRestRepository", collectionResourceRel="zmones")
public interface ZmogusRestRepository extends PagingAndSortingRepository<Zmogus, Integer> {
	
	List<Zmogus> findByRole(@Param("role") String role);
}
