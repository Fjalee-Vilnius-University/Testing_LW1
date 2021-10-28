package com.HelloWebApp.HelloWebApp.jpa;

import com.HelloWebApp.HelloWebApp.model.Saskaita;
import com.HelloWebApp.HelloWebApp.repository.SaskaitaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SaskaitaCommandLineRunner implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(SaskaitaCommandLineRunner.class);
	
	@Autowired
	private SaskaitaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("~~~~~~ SaskaitaCommandLineRunner ~~~~~~");
		repository.save(new Saskaita(8, 3, 5));
		repository.save(new Saskaita(8, 5, 75));
		repository.save(new Saskaita(8, 7, 53));
		repository.save(new Saskaita(2, 2, 30));
		repository.save(new Saskaita(9, 2, 21));
		repository.save(new Saskaita(9, 8, 72));
		repository.save(new Saskaita(6, 7, 50));

		for (Saskaita saskaita : repository.findAll()) {
			log.info(saskaita.toString());
		}
	}
}
