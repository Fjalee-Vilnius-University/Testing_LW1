package com.prekes.web.prekesweb.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.prekes.web.prekesweb.model.Zmogus;
import com.prekes.web.prekesweb.repository.ZmogusRepository;

// CommandLineRunner is invoked on application start

@Component
public class ZmogusCommandLineRunner implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(ZmogusCommandLineRunner.class);
	
	@Autowired
	private ZmogusRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("~~~~~~ ZmogusCommandLineRunner ~~~~~~");
		repository.save(new Zmogus("Jonas", "Admin"));
		repository.save(new Zmogus("Petras", "User"));
		repository.save(new Zmogus("Maryte", "Admin"));
		repository.save(new Zmogus("Janina", "User"));

		for (Zmogus o : repository.findAll()) {
			log.info(o.toString());
		}

		log.info("Admin users are.....");
		for (Zmogus user : repository.findByRole("Admin")) {
			log.info(user.toString());
		}
	}
}
