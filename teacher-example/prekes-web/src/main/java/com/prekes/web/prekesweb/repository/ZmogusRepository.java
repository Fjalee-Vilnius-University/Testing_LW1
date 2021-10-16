package com.prekes.web.prekesweb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prekes.web.prekesweb.model.Zmogus;

public interface ZmogusRepository extends CrudRepository<Zmogus, Integer> {
	
	List<Zmogus> findByRole(String role);
}
