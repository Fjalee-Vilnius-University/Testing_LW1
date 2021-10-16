package com.prekes.web.prekesweb.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.model.Zmogus;
import com.prekes.web.prekesweb.service.PirkimasService;
import com.prekes.web.prekesweb.service.ZmogusService;

/*
@Controller is used to mark classes as Spring MVC Controller.
@RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations
*/

@Controller
public class ZmogusController {
	@Autowired
	ZmogusService service;
	
	@Autowired
	PirkimasService servicePirkimas;
	
	// GET request
	// localhost:8080/list-zmones
	@GetMapping("/list-zmones")		
	public String showAll(ModelMap model) {
		model.put("zmones", service.findAll());
		return "list-zmones";
	}
	
	@GetMapping("/add-zmogus")
	public String showAddPage(ModelMap model) {
		model.addAttribute("zmogus", new Zmogus("", ""));
		return "zmogus";
	}

	@PostMapping("/add-zmogus")
	public String add(ModelMap model, @ModelAttribute("zmogus") Zmogus zm, BindingResult result) {
		if(result.hasErrors()) {
			return "zmogus";
		}
		service.add(zm);
		return "redirect:/list-zmones";
	}

	@GetMapping("/update-zmogus/{zmogausId}")
	public String showUpdatePage(ModelMap model, @PathVariable int zmogausId) {
		model.addAttribute("zmogus", service.findById(zmogausId));
		return "zmogus";
	}

	@PostMapping("/update-zmogus/{zmogausId}")
	public String update(ModelMap model, @ModelAttribute("zmogus") Zmogus zm, BindingResult result) {
		if(result.hasErrors()) {
			return "zmogus";
		}
		service.update(zm);
		return "redirect:/list-zmones";
	}
	
	@GetMapping("/delete-zmogus/{zmogausId}")
	public String delete(@PathVariable int zmogausId) {
		service.deleteById(zmogausId);
		return "redirect:/list-zmones";
	}
	
	/*@PostMapping("add-zmogus")
	public String addZmogus(ModelMap model, @Valid Zmogus zm, BindingResult result) {
		if(result.hasErrors()) {
			return "zmogus";
		}
		service.add(zm);
		return "redirect:/list-zmones";
	}*/
}
