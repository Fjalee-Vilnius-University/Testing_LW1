package com.prekes.web.prekesweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.prekes.web.prekesweb.model.Preke;
import com.prekes.web.prekesweb.service.PrekeService;

//MVC controller
@Controller
public class PrekeController {
	
	@Autowired
	PrekeService service;
	
	// http://localhost:8080/list-prekes
	@GetMapping("/list-prekes")
	public String showAll(ModelMap model) {
		// put the list of all items to model variable "preke"
		// this model is passed to view
		model.put("prekes", service.findAll());
		return "list-prekes"; // view resolver /WEB-INF/jsp/list-prekes.jsp
	}
	
	// http://localhost:8080/add-preke
	@GetMapping("/add-preke")
	public String showAddPage(ModelMap model) {
		model.addAttribute("preke", new Preke("", "", 0));
		return "preke";
	}

	// http://localhost:8080/add-preke
	@PostMapping("/add-preke")
	public String add(ModelMap model, @ModelAttribute("preke") Preke p, BindingResult result) {
		if(result.hasErrors()) {
			return "preke";
		}
		service.add(p);
		return "redirect:/list-prekes";
	}

	// http://localhost:8080/update-preke/1
	@GetMapping("/update-preke/{prekeId}")
	public String showUpdatePage(ModelMap model, @PathVariable int prekeId) {
		model.addAttribute("preke", service.findById(prekeId));
		return "preke";
	}

	// http://localhost:8080/update-preke/1
	@PostMapping("/update-preke/{prekeId}")
	public String update(ModelMap model, @ModelAttribute("preke") Preke p, BindingResult result) {
		if(result.hasErrors()) {
			return "preke";
		}
		service.update(p);
		return "redirect:/list-prekes";
	}
	
	// http://localhost:8080/delete-preke/1
	@GetMapping("/delete-preke/{prekeId}")
	public String delete(@PathVariable int prekeId) {
		service.deleteById(prekeId);
		return "redirect:/list-prekes";
	}
}
