package com.prekes.web.prekesweb.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.repository.PirkimasRepositoryImpl;


@Service
public class PirkimasService {

	@Autowired
	PrekeService prekeService;

	@Autowired
    private PirkimasRepositoryImpl repository;
	
    public List<Pirkimas> findAll() {
        return repository.findAll();
    }

    // pirkimo id sudarytas is triju lauku atskirtu bruksneliais: zmogausId, prekesKodo, datos.
    // pirkimo id = p.getZmogausId()+"-"+p.getPrekesKodas()+"-"+p.getDate()
    public Pirkimas findById(String id) {
        return repository.findById(id);
    }
    
    public List<Pirkimas> findByCustomerId(int customerId) {
    	return repository.findByCustomerId(customerId);
    }
    
    public List<Pirkimas> findByPrekesKodas(int kodas) {
        return repository.findByPrekesKodas(kodas);
    }

    public List<Pirkimas> findByPrekesTitle(String title) {
    	List<Pirkimas> res = new ArrayList<>();

    	Iterator<Pirkimas> iterator = repository.findAll().iterator();
    	while (iterator.hasNext()) {
        	Pirkimas pirk = iterator.next();
        	List<Preke> prekesList = prekeService.findByTitle(pirk.getPrekesPav());
            /*if () {		// TODO
            	res.add(p);
            }*/
        }
        return res;
    }
    
    public List<Pirkimas> findByDate(String date) {
        return repository.findByDate(date);
    }
    
    public void update (Pirkimas p) {
    	repository.update(p);
    }
    
    public Pirkimas add(Pirkimas p) {
        repository.add(p);
        return p;
    }
    
    public void delete(Pirkimas p) {
    	repository.delete(p);
    }
    
    public void updatePrekesPav(Pirkimas pirkimas) {
            Preke p = prekeService.findById(pirkimas.getPrekesKodas());
            pirkimas.setPrekesPav(p.getPavadinimas());
    }
    
    public void updateAllPrekesPav(List<Pirkimas> prikimai) {
    	
    	Iterator<Pirkimas> iterator = prikimai.iterator();
        while (iterator.hasNext()) {
        	Pirkimas p = iterator.next();
            Preke pr = prekeService.findById(p.getPrekesKodas());
            p.setPrekesPav(pr.getPavadinimas());
        }
    }
}
