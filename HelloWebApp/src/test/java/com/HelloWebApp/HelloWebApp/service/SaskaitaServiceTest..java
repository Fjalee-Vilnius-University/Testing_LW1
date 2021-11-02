package com.HelloWebApp.HelloWebApp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.HelloWebApp.HelloWebApp.model.Saskaita;
import com.HelloWebApp.HelloWebApp.repository.SaskaitaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaskaitaServiceTest {
    @Mock
    SaskaitaRepository repository;

    @InjectMocks
    SaskaitaService service;

    @Test
    void testFindAll() {
        var p = new Saskaita();
        List<Saskaita> saskaitos = new ArrayList<>();
        saskaitos.add(p);

        when(repository.findAll()).thenReturn(saskaitos);

        var found = (List<Saskaita>) repository.findAll();

        verify(repository).findAll();

        assertEquals(1, found.size());
    }

    @Test
    void testAdd() {
        var saskaita = new Saskaita(1, 1,1,1, "+37067676987");
        when(repository.save(Mockito.any(Saskaita.class))).thenReturn(saskaita);

        var added = service.add(saskaita);
        verify(repository).save(Mockito.any(Saskaita.class));
        assertNotNull(added);
    }

    @Test
    void testUpdate() {
        var saskaita = new Saskaita(1, 1,1,1, "+37067676987");
        when(repository.save(Mockito.any(Saskaita.class))).thenReturn(saskaita);

        service.update(saskaita);
        verify(repository).save(Mockito.any(Saskaita.class));
    }

    @Test
    void testDeleteById() {
        service.deleteById(1);
        verify(repository).deleteById(Mockito.anyInt());
    }

    @Test
    void testFindById() {
        Saskaita p = new Saskaita(1, 1, 1, 1, "867575865");
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(p));

        Saskaita found = service.findById(1);
        verify(repository).findById(Mockito.anyInt());
        assertNotNull(found);
    }
}
