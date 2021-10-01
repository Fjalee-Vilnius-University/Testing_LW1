package com.prekes.web.prekesweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.prekes.web.prekesweb.model.Pirkimas;
import com.prekes.web.prekesweb.service.PirkimasService;

/*
@Controller is used to mark classes as Spring MVC Controller.
@RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations
*/

// MVC controller
@Controller
public class PirkimasController {
	
	@Autowired
	PirkimasService pirkimasService;
	
	// GET request
	// http://localhost:8080/list-pirkimai
	@GetMapping("/list-pirkimai") 				
	public String showPirkimai(ModelMap model) {
		List<Pirkimas> pirkimai = pirkimasService.findAll();
		pirkimasService.updateAllPrekesPav(pirkimai);

		// put the list of all items to model variable "pirkimai"
		// this model is passed to view
		model.put("pirkimai", pirkimai);
		
		return "list-pirkimai";  // view resolver /WEB-INF/jsp/list-pirkimai.jsp
	}
}
