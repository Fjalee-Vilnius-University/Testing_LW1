package com.HelloWebApp.HelloWebApp.repository;

import com.HelloWebApp.HelloWebApp.model.Saskaita;
import com.HelloWebApp.HelloWebApp.model.TelNr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="saskaitaRestRepository", collectionResourceRel="saskaita")
public interface SaskaitaRepository extends CrudRepository<Saskaita, Integer> {
}
