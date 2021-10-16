package com.prekes.web.prekesweb.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.repository.PrekeRepository;

// CommandLineRunner is invoked on application start

@Component
public class PrekeCommandLineRunner implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(PrekeCommandLineRunner.class);
	
	@Autowired
	private PrekeRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("~~~~~~ PrekeCommandLineRunner ~~~~~~");
		repository.save(new Preke("Pienas", "LT", 1));
		repository.save(new Preke("Duona", "LV", 0.5f));
		repository.save(new Preke("Knyga", "EST", 10));
		repository.save(new Preke("Piestukas", "LT", 0.3f));
		repository.save(new Preke("Pienas", "LV", 1.2f));

		for (Preke o : repository.findAll()) {
			log.info(o.toString());
		}
		
		log.info("Pienas prekes.....");
		for (Preke o : repository.findByPavadinimas("Pienas")) {
			log.info(o.toString());
		}
		
		log.info("Pienas prekesAndSalis.....");
		for (Preke o : repository.findByPavadinimasAndSalis("Pienas", "LV")) {
			log.info(o.toString());
		}
	}

}
