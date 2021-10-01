package com.prekes.web.prekesweb.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.repository.PirkimasRepositoryImpl;

@ExtendWith(MockitoExtension.class)
class PirkimasServiceTest {
	
	@Mock
	PirkimasRepositoryImpl repository;
	
	@InjectMocks
	PirkimasService service;

	@Test
	void testFindAll() {
		Pirkimas p = new Pirkimas();
		List<Pirkimas> pirkimai = new ArrayList<>();
		pirkimai.add(p);
		
		when(repository.findAll()).thenReturn(pirkimai);

		List<Pirkimas> found = repository.findAll();
		
		verify(repository).findAll();
		
		assertEquals(1, found.size());
	}

	@Test
	void testFindByCustomerId() {
		//fail("Not yet implemented");
	}

	@Test
	void testFindByPrekesKodas() {
		//fail("Not yet implemented");
	}

	@Test
	void testFindByPrekesTitle() {
		//fail("Not yet implemented");
	}

	@Test
	void testFindByDate() {
		//fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		//fail("Not yet implemented");
	}

	@Test
	void testAdd() {
		//fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		//fail("Not yet implemented");
	}

	@Test
	void testUpdatePrekesPav() {
		//fail("Not yet implemented");
	}

	@Test
	void testUpdateAllPrekesPav() {
		//fail("Not yet implemented");
	}

}
