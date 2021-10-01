package com.prekes.web.prekesweb.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.repository.PrekeRepositoryImpl;

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
class PrekeServiceSpyTest {

	// Spy is a wrapper round a real implementation
	// use: when(...).thenCallRealMethod();
	@Spy
	PrekeRepositoryImpl repository;
	
	@InjectMocks
	PrekeService service;
	
	@DisplayName("Test Find All")
	@Test
	void testFindAll() {
		Preke p = new Preke(10, "Saldainis", "LT", 3);
		service.add(p);
		
		//when(service.findAll()).thenReturn(prekes);
		when(service.findAll()).thenCallRealMethod(); // use spy and call real mothod implementation 

		List<Preke> found = repository.findAll();
		
		verify(repository).findAll();
		
		assertEquals(4, found.size());
	}

}
