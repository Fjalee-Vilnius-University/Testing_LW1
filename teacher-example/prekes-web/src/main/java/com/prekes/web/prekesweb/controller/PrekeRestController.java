package com.prekes.web.prekesweb.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.service.PrekeService;

/*
@Controller is used to mark classes as Spring MVC Controller.
@RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations
*/

@RestController
public class PrekeRestController {
	@Autowired
	PrekeService service;
	
	// GET request
	// http://localhost:8080/prekes
	@GetMapping("/prekes") 
	public List<Preke> prekesJson() {
		return service.findAll(); // Spring converts java object to -> JSON
	}
	
	// GET request
	// http://localhost:8080/prekes/2
	@GetMapping("/prekes/{prekeId}")
	public Preke PrekeById(@PathVariable int prekeId) {
		return service.findById(prekeId); // Spring converts java object to -> JSON
	}
	
	// POST request
	// http://localhost:8080/prekes	
	// POST request body example JSON: {"kodas":15,"pavadinimas":"CCC","salis":"LV","kainaVnt":0.15}
	// Value of response header 'location' is set to uri of newly created source, e.g., http://localhost:8080/prekes/15
	@PostMapping("/prekes")
	public ResponseEntity<Void> addPreke(@RequestBody Preke newPreke) {
		
		// method parameter newPreke with annotation @RequestBody gets request body
		// request body originally contains JSON and Spring converts it to java object Preke
		Preke p = service.add(newPreke); 

		if (p == null)
			return ResponseEntity.noContent().build();

		// if addition is Success, then return URI of new resource Preke in response header
		// URI = "/preke/{prekeId}"
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(p.getKodas()).toUri();

		// return response with status="created" at "location": 
		return ResponseEntity.created(location).build(); 
	}	
}
