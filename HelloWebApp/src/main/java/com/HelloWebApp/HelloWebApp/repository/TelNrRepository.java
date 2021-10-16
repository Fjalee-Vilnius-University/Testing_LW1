package com.HelloWebApp.HelloWebApp.repository;

import com.HelloWebApp.HelloWebApp.model.TelNr;
import org.springframework.data.repository.CrudRepository;

public interface TelNrRepository extends CrudRepository<TelNr, Integer> {
    TelNr findByTelNr(String telNr);
}
