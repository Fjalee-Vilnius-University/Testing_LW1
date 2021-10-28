package com.HelloWebApp.HelloWebApp.controller;

import com.HelloWebApp.HelloWebApp.model.Saskaita;
import com.HelloWebApp.HelloWebApp.service.SaskaitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/*
@Controller is used to mark classes as Spring MVC Controller.
@RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations
*/

@RestController
public class SaskaitaRestController {
	@Autowired
	SaskaitaService service;

	@GetMapping("/saskaita")
	public List<Saskaita> saskaitaJson() {
		return service.findAll();
	}
	
	@GetMapping("/saskaita/{id}")
	public Saskaita SaskaitaById(@PathVariable int id) {
		return service.findById(id);
	}

	@PostMapping("/saskaita")
	public ResponseEntity<Void> addSaskaita(@RequestBody Saskaita newSaskaita) {
		Saskaita nr = service.add(newSaskaita);

		if (nr == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(nr.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	/*@PostMapping("/delete-saskaita/{telNr}")
	public void deleteSaskaita(@PathVariable int telNr){
		service.deleteByTelNr("+37061111111");
	}*/
}
