package com.prekes.web.prekesweb.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.prekes.web.prekesweb.model.Preke;

//@Component
@Repository
public class PrekeRepositoryImpl {
	private static List<Preke> prekes = new ArrayList<>();

    static {
        prekes.add(new Preke(1, "Pienas", "LT", 1));
        prekes.add(new Preke(2, "Duona", "LV", 0.5f));
        prekes.add(new Preke(3, "Knyga", "EST", 10));
    }
    
    public List<Preke> findAll() {
    	return prekes;
    }

    public Preke findById(int id) {
        Iterator<Preke> iterator = prekes.iterator();
        while (iterator.hasNext()) {
            Preke p = iterator.next();
            if (p.getKodas() == id) {
                return p;
            }
        }
        return null;
    }
    
    public List<Preke> findByTitle(String title) {
        List<Preke> res = new ArrayList<>();
        for (Preke item : prekes) {
            if (item.getPavadinimas().equals(title)) {
            	res.add(item);
            }
        }
        return res;
    }
    
    public void update (Preke p) {
//    	prekes.remove(p);
//    	prekes.add(p);
    	Preke pp = prekes.get(p.getKodas());
    	pp = p;
    }
    
    public Preke add(Preke p) {
        prekes.add(p);
        return p;
    }
    
    public void deleteById(int id) {
        Iterator<Preke> iterator = prekes.iterator();
        while (iterator.hasNext()) {
            Preke p = iterator.next();
            if (p.getKodas() == id) {
                iterator.remove();
            }
        }
    }
    
    public void delete(Preke p) {
    	deleteById(p.getKodas());
    }
}
