package com.prekes.web.prekesweb.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.prekes.web.prekesweb.model.Zmogus;

@Repository
public class ZmogusRepositoryImpl {
	private static List<Zmogus> zmones = new ArrayList<>();

    static {
    	zmones.add(new Zmogus(1, "Jonas"));
    	zmones.add(new Zmogus(2, "Petras"));
    }
    
    public List<Zmogus> findAll() {
    	return zmones;
    }

    public Zmogus findById(int id) {
        Iterator<Zmogus> iterator = zmones.iterator();
        while (iterator.hasNext()) {
            Zmogus z = iterator.next();
            if (z.getId() == id) {
                return z;
            }
        }
        return null;
    }
}
