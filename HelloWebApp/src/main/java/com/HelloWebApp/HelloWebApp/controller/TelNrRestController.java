package com.HelloWebApp.HelloWebApp.controller;

import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.service.SaskaitaService;
import com.HelloWebApp.HelloWebApp.service.TelNrService;
import com.HelloWebApp.HelloWebApp.service.TelNrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/*
@Controller is used to mark classes as Spring MVC Controller.
@RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations
*/

@RestController
public class TelNrRestController {
	@Autowired
	TelNrService telNrService;
	@Autowired
	SaskaitaService saskaitaService;

	@GetMapping("/telNr")
	public List<TelNr> telNrJson() {
		return telNrService.findAll();
	}

	@GetMapping("/telNr/{id}")
	public TelNr TelNrById(@PathVariable int id) {
		return telNrService.findById(id);
	}

	@PostMapping("/telNr")
	public ResponseEntity<Void> addTelNr(@RequestBody TelNr newTelNr) {
		TelNr nr = telNrService.add(newTelNr);

		if (nr == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(nr.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/telNr/{id}")
	public ResponseEntity<Void> deleteTelNr(@PathVariable int id) {
		telNrService.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
