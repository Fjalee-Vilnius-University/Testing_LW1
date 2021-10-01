package com.prekes.web.prekesweb.repository;

import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.prekes.web.prekesweb.model.Preke;

//@Component
@Repository
public class PrekeMapRepositoryImpl {
	private static HashMap<Integer, Preke> prekes = new HashMap<>();

    static {
        prekes.put(1, new Preke(1, "Pienas", "LT", 1));
        prekes.put(2, new Preke(2, "Duona", "LV", 0.5f));
        prekes.put(3, new Preke(3, "Knyga", "EST", 10));
    }
    
    public HashMap<Integer, Preke> findAll() {
    	return prekes;
    }

    public Preke findById(int id) {
    	if(prekes.containsKey(id)) 
    		return prekes.get(id);
        return null;
    }
    
    public HashMap<Integer, Preke> findByTitle(String title) {
    	HashMap<Integer, Preke> res = new HashMap<>();
        for (Entry<Integer, Preke> item : prekes.entrySet()) {
            if (item.getValue().getPavadinimas().equals(title)) {
            	res.put(item.getValue().getKodas(), item.getValue());
            }
        }
        return res;
    }
    
    public void update (Preke p) {
    	prekes.replace(p.getKodas(), p);
    }
    
    public Preke add(Preke p) {
    	prekes.put(p.getKodas(), p);
        return p;
    }
    
    public void deleteById(int id) {
    	prekes.remove(id);
    }
    
    public void delete(Preke p) {
    	deleteById(p.getKodas());
    }
}
