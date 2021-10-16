package com.prekes.web.prekesweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.model.Zmogus;
//import com.prekes.web.prekesweb.repository.ZmogusRepositoryImpl;
import com.prekes.web.prekesweb.repository.ZmogusRepository;

@Service
public class ZmogusService {

	@Autowired
	PirkimasService pirkimasService;

	@Autowired
	private ZmogusRepository repository; //private ZmogusRepositoryImpl repository;
	
    public List<Zmogus> findAll() {
        return (List<Zmogus>)repository.findAll();
    }
    
    public Zmogus findById(int customerId) {
    	return repository.findById(customerId).get();
    }
    
    public List<Pirkimas> findPirkimaiByCustomerId(int customerId) {
        return pirkimasService.findByCustomerId(customerId);
    }
    
    public int findMaxId() {
    	Iterable<Zmogus> zmones = repository.findAll();
    	int max = 0;
    	for(Zmogus o : zmones) {
    		if(o.getId() > max) max = o.getId();
    	}
    	return max;
    }
    
    public void update (Zmogus zm) {
    	//repository.update(zm);
    	repository.save(zm);
    }
    
    public Zmogus add(Zmogus zm) {
    	return repository.save(zm);
    }
    
    public void deleteById(int id) {
    	repository.deleteById(id);	
    }
    
    public void delete(Zmogus zm) {
    	repository.delete(zm);	
    }
}
