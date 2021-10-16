package com.prekes.web.prekesweb.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.repository.PrekeRepository;

/*
 * TODO
 * Paziureti TestCoverage rezultatus
 * Desinias peles mygtukas -> Coverage As - > JUnit test
 * arba
 * meniu mygtukas "zalas trikampis su zalia-raudona staciakampeliu"
 * arba
 * Meniu Run -> Coverage As - > JUnit test
 */


@ExtendWith(MockitoExtension.class)
class PrekeServiceTest {

	@Mock
	PrekeRepository repository;
	
	@InjectMocks
	PrekeService service;
	
	@DisplayName("Test Find All")
	@Test
	void testFindAll() {
		Preke p = new Preke("", "", 0);
		List<Preke> prekes = new ArrayList<>();
		prekes.add(p);
		
		when(repository.findAll()).thenReturn(prekes); // static method when daromas tada, kai metodas returnina reiksme

		List<Preke> found = service.findAll();
		
		verify(repository).findAll(); // called 1 time
		
		assertEquals(1, found.size());
	}

	@Test
	void testFindById() {
		Preke p = new Preke("", "", 0);
		when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(p)); // static method when daromas tada, kai metodas returnina reiksme
		
		Preke found = service.findById(1);
		verify(repository).findById(Mockito.anyInt()); // verifying mock call 1 time
		assertNotNull(found);
	}

	@Test
	void testFindByTitle() {
		List<Preke> prekes = new ArrayList<Preke>();
		prekes.add(new Preke("AAA", "LT", 0));
		when(repository.findAll()).thenReturn(prekes);
		
		List<Preke> found = service.findByTitle("AAA");
		verify(repository).findAll();
		assertNotNull(found);
	}

	@Test
	void testUpdate() {
		Preke p = new Preke("", "", 0);
		service.update(p);
		verify(repository).save(Mockito.any(Preke.class)); // verify that mock method was called one time
	}

	@Test
	void testAdd() {
		//Preke p = new Preke(1, "aaa", "LT", 2);
		Preke p = new Preke("", "", 0);
		when(repository.save(Mockito.any(Preke.class))).thenReturn(p);
		
		Preke added = service.add(p);
		verify(repository).save(Mockito.any(Preke.class));
		assertNotNull(added);
	}

	@Test
	void testDeleteById() {
		service.deleteById(1);
		verify(repository).deleteById(Mockito.anyInt()); // verify that mock method was called one time

//		verify(repository).deleteById(1);
//		verify(repository, never()).deleteById(2);
	}

	@Test
	void testDelete() {
		Preke p = new Preke("", "", 0);
		service.delete(p);
		verify(repository).delete(Mockito.any(Preke.class));
	}

}
