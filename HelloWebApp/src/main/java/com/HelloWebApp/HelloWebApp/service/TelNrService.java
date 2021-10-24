package com.HelloWebApp.HelloWebApp.service;

import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.repository.TelNrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelNrService {

    @Autowired
    private TelNrRepository repository;

    public void update (TelNr telNr) {
        repository.save(telNr);
    }
    public List<TelNr> findAll() {
        return (List<TelNr>)repository.findAll();
    }
    public void deleteById(int id) {
        repository.deleteById(id);
    }
    public TelNr add (TelNr telNr) { return repository.save(telNr); }

}
