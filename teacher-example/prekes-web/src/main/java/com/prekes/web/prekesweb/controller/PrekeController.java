package com.prekes.web.prekesweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.prekes.web.prekesweb.service.PrekeService;

//MVC controller
@Controller
public class PrekeController {
	
	@Autowired
	PrekeService service;
	
	// GET request
	// http://localhost:8080/list-prekes
	@GetMapping("/list-prekes")
	public String showPrekes(ModelMap model) {
		
		// put the list of all items to model variable "preke"
		// this model is passed to view
		model.put("prekes", service.findAll());

		return "list-prekes"; // view resolver /WEB-INF/jsp/list-prekes.jsp
	}
	
	
}
