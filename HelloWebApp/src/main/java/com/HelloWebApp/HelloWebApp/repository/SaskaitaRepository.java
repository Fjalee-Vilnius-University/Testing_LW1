package com.HelloWebApp.HelloWebApp.repository;

import com.HelloWebApp.HelloWebApp.model.TelNr;
import org.springframework.data.repository.CrudRepository;

public interface SaskaitaRepository extends CrudRepository<TelNr, Integer> {
}
