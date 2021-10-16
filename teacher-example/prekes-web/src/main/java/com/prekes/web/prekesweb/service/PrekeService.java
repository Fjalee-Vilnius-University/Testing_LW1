package com.prekes.web.prekesweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.repository.PrekeRepository;

//@Component
@Service
public class PrekeService {

	@Autowired
    private PrekeRepository repository;

    public List<Preke> findAll() {
    	return (List<Preke>) repository.findAll();
    }
    
    public Preke findById(int id) {
        return repository.findById(id).get();
    }
    
    public int findMaxId() {
    	Iterable<Preke> prekes = repository.findAll();
    	int max = 0;
    	for(Preke o : prekes) {
    		if(o.getKodas() > max) max = o.getKodas();
    	}
    	return max;
    }    
    public List<Preke> findByTitle(String title) {
    	List<Preke> prekes = new ArrayList<Preke>();
    	for(Preke o : repository.findAll()) {
    		if(o.getPavadinimas().equals(title)) 
    			prekes.add(o);
    	}
        return prekes;
    }
    
    public void update (Preke p) {
    	repository.save(p);
    }
    
    public Preke add(Preke p) {
    	return repository.save(p);
    }
    
    public void deleteById(int id) {
    	repository.deleteById(id);	
    }
    
    public void delete(Preke p) {
    	repository.delete(p);	
    }
}
