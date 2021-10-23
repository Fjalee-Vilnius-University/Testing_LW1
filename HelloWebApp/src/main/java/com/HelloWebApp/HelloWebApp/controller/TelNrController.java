package com.HelloWebApp.HelloWebApp.controller;

import com.HelloWebApp.HelloWebApp.service.TelNrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TelNrController {
    @Autowired
    TelNrService telNrService;

    @GetMapping("/list-telNr")
    public String showTelNr(ModelMap model) {
        model.put("telNr", telNrService.findAll());
        return "list-telNr";
    }

    @GetMapping("/delete-telNr/{id}")
    public String deleteTelNr(@PathVariable int id) {
        telNrService.deleteById(id);
        return "redirect:/list-telNr";
    }
}
