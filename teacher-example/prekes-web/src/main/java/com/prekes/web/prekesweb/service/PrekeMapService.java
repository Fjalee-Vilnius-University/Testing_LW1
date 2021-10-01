package com.prekes.web.prekesweb.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.repository.PrekeMapRepositoryImpl;

@Service
public class PrekeMapService {
	
	@Autowired
    private PrekeMapRepositoryImpl repository;

    public HashMap<Integer, Preke> findAll() {
    	return repository.findAll();
    }
    
    public Preke findById(int id) {
        return repository.findById(id);
    }
    
    public HashMap<Integer, Preke> findByTitle(String title) {
        return repository.findByTitle(title);
    }
    
    public void update (Preke p) {
    	repository.update(p);
    }
    
    public Preke add(Preke p) {
    	return repository.add(p);
    }
    
    public void deleteById(int id) {
    	repository.deleteById(id);	
    }
    
    public void delete(Preke p) {
    	repository.delete(p);	
    }
}
