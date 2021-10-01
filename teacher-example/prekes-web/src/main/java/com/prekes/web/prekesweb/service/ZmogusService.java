package com.prekes.web.prekesweb.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.model.Zmogus;
import com.prekes.web.prekesweb.repository.ZmogusRepositoryImpl;

@Service
public class ZmogusService {

	@Autowired
	PirkimasService pirkimasService;

	@Autowired
    private ZmogusRepositoryImpl repository;
	
    public List<Zmogus> findAll() {
        return repository.findAll();
    }
    
    public Zmogus findById(int customerId) {
    	return repository.findById(customerId);
    }
    
    public List<Pirkimas> findPirkimaiByCustomerId(int customerId) {
        return pirkimasService.findByCustomerId(customerId);
    }
}
