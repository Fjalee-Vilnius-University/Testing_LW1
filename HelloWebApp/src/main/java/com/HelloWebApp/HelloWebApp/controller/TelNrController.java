package com.HelloWebApp.HelloWebApp.controller;

import com.HelloWebApp.HelloWebApp.model.TelNr;
import com.HelloWebApp.HelloWebApp.service.SaskaitaService;
import com.HelloWebApp.HelloWebApp.service.TelNrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TelNrController {
    @Autowired
    TelNrService telNrService;
    @Autowired
    SaskaitaService saskaitaService;

    @GetMapping("/list-telNr")
    public String showTelNr(ModelMap model) {
        model.put("telNr", telNrService.findAll());
        return "list-telNr";
    }

    @GetMapping("/delete-telNr/{id}")
    public String deleteTelNr(@PathVariable int id) {
        telNrService.deleteById(id);
        saskaitaService.deleteByTelNrId(id);

        return "redirect:/list-telNr";
    }

    @GetMapping("/update-telNr/{id}")
    public String showUpdatePage(ModelMap model, @PathVariable int id) {
        model.addAttribute("telNr", telNrService.findById(id));
        return "telNr";
    }

    @PostMapping("/update-telNr/{id}")
    public String update(@ModelAttribute("telNr") TelNr telNr, BindingResult result) {
        if(result.hasErrors()) {
            return "telNr";
        }

        telNrService.update(telNr);

        return "redirect:/list-telNr";
    }
}
