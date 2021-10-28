package com.HelloWebApp.HelloWebApp.repository;

import com.HelloWebApp.HelloWebApp.model.TelNr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="telNrRestRepository", collectionResourceRel="telNr")
public interface TelNrRepository extends CrudRepository<TelNr, Integer> {
    TelNr findByNr(String nr);
}
