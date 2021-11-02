package com.HelloWebApp.HelloWebApp.repository;

import com.HelloWebApp.HelloWebApp.model.Saskaita;
import com.HelloWebApp.HelloWebApp.model.TelNr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SaskaitaRepositoryTest {
    @Autowired
    SaskaitaRepository repository;

    private int id = 1;
    private int telNrId = 2;
    private int menuo = 2;
    private int suma = 45;
    private String telNr = "+370656765";
    private Saskaita saskaita = new Saskaita(id, telNrId, menuo, suma, telNr);

    @BeforeEach
    public void setup(){
        saskaita = new Saskaita(id, telNrId, menuo, suma, telNr);
    }

    @Test
    public void save_works_correctly(){
        repository.save(saskaita);

        var saskaitaById = repository.findById(id);

        assertNotNull(saskaitaById);
        assertEquals(saskaita.getId(), id);
    }

    @Test
    public void delete_works_correctly(){
        repository.save(saskaita);
        var saskaitaById = repository.findById(id);
        assertNotNull(saskaitaById);

        repository.delete(saskaita);
        saskaitaById = repository.findById(id);
        assertNull(saskaitaById);
    }

    @Test
    public void findAll_works_correctly(){
        repository.save(saskaita);
        var saskaitos = repository.findAll();

        assertNotNull(saskaitos);

        var result = new ArrayList<Saskaita>();
        saskaitos.forEach(result::add);

        assertEquals(1, result.size());
    }
}