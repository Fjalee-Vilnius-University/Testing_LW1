package com.prekes.web.prekesweb.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.model.Zmogus;
import com.prekes.web.prekesweb.service.PirkimasService;
import com.prekes.web.prekesweb.service.ZmogusService;

/*
@Controller is used to mark classes as Spring MVC Controller.
@RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations
*/

@RequestMapping("/zmones")
@RestController
public class ZmogusRestController {
	@Autowired
	ZmogusService service;
	
	@Autowired
	PirkimasService servicePirkimas;
	
	// GET request localhost:8080/zmones
	@GetMapping(produces = {"application/json"}) //@GetMapping("/zmones")		
	public ResponseEntity<List<Zmogus>> zmonesJson() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	// GET request localhost:8080/zmones/2
	@GetMapping(path="/{zmogausId}", produces = {"application/json"})     
	public ResponseEntity<Zmogus> zmogusById(@PathVariable int zmogausId) {
		return new ResponseEntity<>(service.findById(zmogausId), HttpStatus.OK);
	}
	
	// GET request localhost:8080/zmones/1/pirkimai
	@GetMapping(path="/{zmogausId}/pirkimai", produces = {"application/json"})
	public ResponseEntity<List<Pirkimas>> findPirkimaiForZmogus(@PathVariable int zmogausId) {
		return new ResponseEntity<>(service.findPirkimaiByCustomerId(zmogausId), HttpStatus.OK);
	}	
	
	// POST request
	// http://localhost:8080/zmones/1/pirkimai	
	// POST request body example JSON: {"zmogausId":1,"prekesKodas":2,"vnt":10,"date":"2222-01-01","prekesPav":null}
	// Value of response header 'location' is set to uri of newly created source, 
	// e.g., http://localhost:8080/zmones/1/pirkimai/1-2-2222-01-01
	@PostMapping(path="/{zmogausId}/pirkimai") 
	public ResponseEntity<Void> addPirkimasToZmogus(@PathVariable String zmogausId, @RequestBody Pirkimas newPirkimas) {

		// method parameter newPirkimas with annotation @RequestBody gets request body
		// request body originally contains JSON and Spring converts it to java object Pirkimas
		Pirkimas p = servicePirkimas.add(newPirkimas);

		if (p == null)
			return ResponseEntity.noContent().build(); // status: 204 No content

		// if addition is Success, then return URI of new resource Pirkimas in response header
		// URI = "/zmones/{zmogausId}/pirkimai/{pirkimoId}"
		// where pirkimoId consists from three fields: zmogausId, prekesKodas, data.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(p.getZmogausId()+"-"+p.getPrekesKodas()+"-"+p.getDate()).toUri();

		// return response with status="created" at "location": 
		return ResponseEntity.created(location).build();  // status: 201 Created
	}
	
	// GET request http://localhost:8080/zmones/1/pirkimai/1-1-2021-09-01
	@GetMapping(path="/{zmogausId}/pirkimai/{pirkimoId}", produces = {"application/json"}) 
	public ResponseEntity<Pirkimas> retrieveDetailsForPirkimas(@PathVariable int zmogausId, @PathVariable String pirkimoId) {
		return new ResponseEntity<>(servicePirkimas.findById(pirkimoId), HttpStatus.OK);
	}
}
