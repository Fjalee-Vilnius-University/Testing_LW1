package com.HelloWebApp.HelloWebApp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.repository.TelNrRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TelNrServiceTest {
    @Mock
    TelNrRepository repository;

    @InjectMocks
    TelNrService service;

    @Test
    void testFindAll() {
        var p = new TelNr();
        List<TelNr> telNrs = new ArrayList<>();
        telNrs.add(p);

        when(repository.findAll()).thenReturn(telNrs);

        var found = (List<TelNr>) repository.findAll();

        verify(repository).findAll();

        assertEquals(1, found.size());
    }

    @Test
    void testAdd() {
        var TelNr = new TelNr("+37067676987", 1);
        when(repository.save(Mockito.any(TelNr.class))).thenReturn(TelNr);

        var added = service.add(TelNr);
        verify(repository).save(Mockito.any(TelNr.class));
        assertNotNull(added);
    }

    @Test
    void testUpdate() {
        var TelNr = new TelNr("+37067676987", 1);
        when(repository.save(Mockito.any(TelNr.class))).thenReturn(TelNr);

        service.update(TelNr);
        verify(repository).save(Mockito.any(TelNr.class));
    }

    @Test
    void testDeleteById() {
        service.deleteById(1);
        verify(repository).deleteById(Mockito.anyInt());
    }

    @Test
    void testFindById() {
        TelNr p = new TelNr("867575865", 1);
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(p));

        TelNr found = service.findById(1);
        verify(repository).findById(Mockito.anyInt());
        assertNotNull(found);
    }
}
