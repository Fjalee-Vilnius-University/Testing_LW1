package com.HelloWebApp.HelloWebApp.controller;

import com.HelloWebApp.HelloWebApp.model.Saskaita;
import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.service.SaskaitaService;
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
public class SaskaitaRestController {
	@Autowired
	SaskaitaService saskaitaService;
	@Autowired
	TelNrService telNrService;

	@GetMapping("/saskaita")
	public List<Saskaita> saskaitaJson() {
		return saskaitaService.findAll();
	}
	
	@GetMapping("/saskaita/{id}")
	public Saskaita SaskaitaById(@PathVariable int id) {
		return saskaitaService.findById(id);
	}

	@PostMapping("/saskaita")
	public ResponseEntity<Void> addSaskaita(@RequestBody Saskaita newSaskaita) {
		SetTelNr(newSaskaita);
		Saskaita nr = saskaitaService.add(newSaskaita);

		if (nr == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(nr.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/saskaita/{id}")
	public ResponseEntity<Void> deleteSaskaita(@PathVariable int id) {
		saskaitaService.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	private void SetTelNr(Saskaita saskaita){
		TelNr telNr = telNrService.findById(saskaita.getTelNrId());
		if (telNr != null){
			saskaita.setTelNr(telNr.getNr());
		}
		else{
			saskaita.setTelNr("Tel.Nr. not found");
		}
	}
}
