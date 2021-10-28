package com.HelloWebApp.HelloWebApp.service;

import com.HelloWebApp.HelloWebApp.model.Saskaita;
import com.HelloWebApp.HelloWebApp.repository.SaskaitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SaskaitaService {
    @Autowired
    private SaskaitaRepository repository;

    public void update (Saskaita saskaita) {
        repository.save(saskaita);
    }
    public List<Saskaita> findAll() {
        return (List<Saskaita>)repository.findAll();
    }
    public void deleteById(int id) {
        repository.deleteById(id);
    }
    public Saskaita add (Saskaita saskaita) { return repository.save(saskaita); }
    public Saskaita findById (int id) { return repository.findById(id).get(); }
    public void deleteByTelNrId(int telNrId) {
        for (Saskaita saskaita : repository.findAll()) {
            if (saskaita.getTelNrId() == telNrId){
                deleteById(saskaita.getId());
            }
        }
    }
}
