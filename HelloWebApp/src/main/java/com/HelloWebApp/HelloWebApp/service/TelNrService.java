package com.HelloWebApp.HelloWebApp.service;

import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.repository.TelNrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelNrService {

    @Autowired
    private TelNrRepository repository;

    public void update (TelNr telNr) {
        repository.save(telNr);
    }
}
