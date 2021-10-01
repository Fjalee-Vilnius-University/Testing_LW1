package com.prekes.web.prekesweb.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.prekes.web.prekesweb.model.Pirkimas;

//@Component
@Repository
public class PirkimasRepositoryImpl {
    private static List<Pirkimas> pirkimai = new ArrayList<>();

    static {
    	pirkimai.add(new Pirkimas(1, 1, 2, "2021-09-01"));
    	pirkimai.add(new Pirkimas(2, 1, 3, "2021-09-01"));
    	pirkimai.add(new Pirkimas(2, 2, 5, "2021-09-02"));
    	pirkimai.add(new Pirkimas(1, 3, 5, "2021-09-02"));
    	pirkimai.add(new Pirkimas(1, 1, 6, "2021-09-02"));
    	pirkimai.add(new Pirkimas(2, 3, 2, "2021-09-02"));
    	pirkimai.add(new Pirkimas(1, 2, 5, "2021-09-02"));
    	pirkimai.add(new Pirkimas(2, 1, 5, "2021-09-01"));
    	pirkimai.add(new Pirkimas(2, 3, 5, "2021-09-01"));
    }

    
    public List<Pirkimas> findAll() {
    	return pirkimai;
    }

    // pirkimo id sudarytas is triju lauku atskirtu bruksneliais: zmogausId, prekesKodo, datos.
    // pirkimo id = p.getZmogausId()+"-"+p.getPrekesKodas()+"-"+p.getDate()
    public Pirkimas findById(String id) { 
    	
    	Iterator<Pirkimas> iterator = pirkimai.iterator();
        while (iterator.hasNext()) {
        	Pirkimas p = iterator.next();
            if (id.equals(p.getZmogausId()+"-"+p.getPrekesKodas()+"-"+p.getDate())) {
            	return(p);
            }
        }
        return null;
    }
    
    public List<Pirkimas> findByCustomerId(int customerId) {
    	List<Pirkimas> res = new ArrayList<>();
    	
    	Iterator<Pirkimas> iterator = pirkimai.iterator();
        while (iterator.hasNext()) {
        	Pirkimas p = iterator.next();
            if (p.getZmogausId() == customerId) {
            	res.add(p);
            }
        }
        return res;
    }

    public List<Pirkimas> findByPrekesKodas(int kodas) {
    	List<Pirkimas> res = new ArrayList<>();

    	Iterator<Pirkimas> iterator = pirkimai.iterator();
    	while (iterator.hasNext()) {
        	Pirkimas p = iterator.next();
            if (p.getPrekesKodas() == kodas) {
            	res.add(p);
            }
        }
        return res;
    }
    
    public List<Pirkimas> findByDate(String date) {
    	List<Pirkimas> res = new ArrayList<>();

    	Iterator<Pirkimas> iterator = pirkimai.iterator();
    	while (iterator.hasNext()) {
        	Pirkimas p = iterator.next();
            if ((p.getDate()).equals(date)) {
            	res.add(p);
            }
        }
        return res;
    }
    
    public void update (Pirkimas pNew) {
    	Iterator<Pirkimas> iterator = pirkimai.iterator();
        while (iterator.hasNext()) {
        	Pirkimas p = iterator.next();
            if (p.equals(pNew)) {
                p = pNew;
            }
        }
    }
    
    public Pirkimas add(Pirkimas p) {
    	pirkimai.add(p);
    	return p;
    }
    
    public void delete(Pirkimas pToDelete) {
        Iterator<Pirkimas> iterator = pirkimai.iterator();
        while (iterator.hasNext()) {
        	Pirkimas p = iterator.next();
            if (p.equals(pToDelete)) {
                iterator.remove();
            }
        }
    }
}
