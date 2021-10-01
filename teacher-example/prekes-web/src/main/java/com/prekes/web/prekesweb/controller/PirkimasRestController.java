package com.prekes.web.prekesweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.service.PirkimasService;

/*
@Controller is used to mark classes as Spring MVC Controller.
@RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations
*/

@RestController
public class PirkimasRestController {
	
	@Autowired
	PirkimasService pirkimasService;
	
	// GET request
	// http://localhost:8080/list-pirkimai-json
	@GetMapping("/list-pirkimai-json")
	public List<Pirkimas> showPirkimaiJson() {
		List<Pirkimas> pirkimai = pirkimasService.findAll();
		pirkimasService.updateAllPrekesPav(pirkimai);
		return pirkimai;
	}
}
