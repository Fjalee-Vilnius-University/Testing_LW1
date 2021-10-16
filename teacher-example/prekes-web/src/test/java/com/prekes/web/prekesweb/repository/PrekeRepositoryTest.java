package com.prekes.web.prekesweb.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.prekes.web.prekesweb.model.Preke;

@DataJpaTest
class PrekeRepositoryTest {

	@Autowired
	private PrekeRepository prekeRepository;

	@Test
	public void testSave() {
		Preke o = new Preke("AAA", "BBB", 2.5f);
		prekeRepository.save(o);

		Preke prekeByPavadinimas = prekeRepository.findOneByPavadinimas("AAA");

		assertNotNull(prekeByPavadinimas);
		assertEquals("BBB", prekeByPavadinimas.getSalis());
		System.out.println(prekeByPavadinimas);
	}

	@Test
	public void testFindAll() {
		Preke o = new Preke("AAA", "BBB", 2.5f);
		prekeRepository.save(o);

		Iterable<Preke> prekes = prekeRepository.findAll();

		assertNotNull(prekes);

		// get List<Preke> from Iterable<Preke>
		List<Preke> result = new ArrayList<Preke>();
		prekes.forEach(result::add);
		// List<Preke> result = StreamSupport.stream(prekes.spliterator(), false).collect(Collectors.toList());

		assertEquals(1, result.size());
	}

	@Test
	public void testDelete() {
		Preke o = new Preke("AAA", "BBB", 2.5f);
		prekeRepository.save(o);

		Preke prekeByPavadinimas = prekeRepository.findOneByPavadinimas("AAA");
		assertNotNull(prekeByPavadinimas);

		prekeRepository.delete(prekeByPavadinimas);

		Iterable<Preke> prekes = prekeRepository.findAll();

		// get List<Preke> from Iterable<Preke>
		List<Preke> result = StreamSupport.stream(prekes.spliterator(), false).collect(Collectors.toList());

		assertEquals(0, result.size());
	}

	@Test
	public void testfindByPavadinimas() {
		Preke o0 = new Preke("AAA", "BBB", 2.5f);
		Preke o1 = new Preke("AAA", "CCC", 1.5f);
		Preke o2 = new Preke("aaa", "BBB", 2.5f);
		prekeRepository.save(o0);
		prekeRepository.save(o1);
		prekeRepository.save(o2);

		Iterable<Preke> prekes = prekeRepository.findByPavadinimas("AAA");

		assertNotNull(prekes);

		// get List<Preke> from Iterable<Preke>
		List<Preke> result = StreamSupport.stream(prekes.spliterator(), false).collect(Collectors.toList());

		assertEquals(2, result.size());
	}

	@Test
	public void testFindByPavadinimasAndSalis() {
		Preke o0 = new Preke("AAA", "BBB", 2.5f);
		Preke o1 = new Preke("AAA", "CCC", 1.5f);
		Preke o2 = new Preke("aaa", "BBB", 2.5f);
		prekeRepository.save(o0);
		prekeRepository.save(o1);
		prekeRepository.save(o2);

		Iterable<Preke> prekes = prekeRepository.findByPavadinimasAndSalis("AAA", "BBB");

		assertNotNull(prekes);

		// get List<Preke> from Iterable<Preke>
		List<Preke> result = StreamSupport.stream(prekes.spliterator(), false).collect(Collectors.toList());

		assertEquals(1, result.size());
	}
}
