package com.HelloWebApp.HelloWebApp.repository;

import com.HelloWebApp.HelloWebApp.model.TelNr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TelNrRepositoryTest {
    @Autowired TelNrRepository repository;

    private String nr = "+37065645678";
    private int userId = 2;
    private TelNr telNr;

    @BeforeEach
    public void setup(){
        telNr = new TelNr(nr, userId);
    }

    @Test
    public void save_works_correctly(){
        repository.save(telNr);

        var telNrById = repository.findByNr(nr);

        assertNotNull(telNrById);
        assertEquals(telNrById.getNr(), nr);
    }

    @Test
    public void delete_works_correctly(){
        repository.save(telNr);
        var telNrById = repository.findByNr(nr);
        assertNotNull(telNrById);

        repository.delete(telNr);
        telNrById = repository.findByNr(nr);
        assertNull(telNrById);
    }

    @Test
    public void findAll_works_correctly(){
        repository.save(telNr);
        var telNrs = repository.findAll();

        assertNotNull(telNrs);

        var result = new ArrayList<TelNr>();
        telNrs.forEach(result::add);

        assertEquals(1, result.size());
    }
}