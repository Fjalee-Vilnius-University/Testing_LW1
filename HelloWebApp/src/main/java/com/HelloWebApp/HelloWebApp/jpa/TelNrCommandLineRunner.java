package com.HelloWebApp.HelloWebApp.jpa;

import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.repository.TelNrRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TelNrCommandLineRunner implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(TelNrCommandLineRunner.class);
	
	@Autowired
	private TelNrRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("~~~~~~ TelNrCommandLineRunner ~~~~~~");
		repository.save(new TelNr("+37061111111", 1));
		repository.save(new TelNr("+37062222222", 2));
		repository.save(new TelNr("+37063333333", 3));
		repository.save(new TelNr("+37064444444", 4));
		repository.save(new TelNr("+37065555555", 5));

		for (TelNr telNr : repository.findAll()) {
			log.info(telNr.toString());
		}

		log.info(repository.findByNr("+37063333333").toString());
	}
}
