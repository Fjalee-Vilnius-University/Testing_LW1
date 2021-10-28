package com.HelloWebApp.HelloWebApp.controller;

import java.net.URI;
import java.util.List;

import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.service.TelNrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/*
@Controller is used to mark classes as Spring MVC Controller.
@RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations
*/

@RestController
public class TelNrRestController {
	@Autowired
	TelNrService service;

	@GetMapping("/telNr")
	public List<TelNr> telNrJson() {
		return service.findAll();
	}
	
	@GetMapping("/telNr/{id}")
	public TelNr TelNrById(@PathVariable int id) {
		return service.findById(id);
	}

	@PostMapping("/telNr")
	public ResponseEntity<Void> addTelNr(@RequestBody TelNr newTelNr) {
		TelNr nr = service.add(newTelNr);

		if (nr == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(nr.getId()).toUri();

		return ResponseEntity.created(location).build();
	}	
}
