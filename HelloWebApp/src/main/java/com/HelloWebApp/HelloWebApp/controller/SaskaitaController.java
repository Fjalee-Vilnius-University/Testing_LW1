package com.HelloWebApp.HelloWebApp.controller;

import com.HelloWebApp.HelloWebApp.model.Saskaita;
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
public class SaskaitaController {
    @Autowired
    SaskaitaService saskaitaService;
    @Autowired
    TelNrService telNrService;

    @GetMapping("/list-saskaita")
    public String showSaskaita(ModelMap model) {
        model.put("saskaita", saskaitaService.findAll());
        return "list-saskaita";
    }

    @GetMapping("/delete-saskaita/{id}")
    public String deleteSaskaita(@PathVariable int id) {
        saskaitaService.deleteById(id);
        return "redirect:/list-saskaita";
    }

    @GetMapping("/update-saskaita/{id}")
    public String showUpdatePage(ModelMap model, @PathVariable int id) {
        model.addAttribute("saskaita", saskaitaService.findById(id));
        return "saskaita";
    }

    @PostMapping("/update-saskaita/{id}")
    public String update(@ModelAttribute("saskaita") Saskaita saskaita, BindingResult result) {
        if(result.hasErrors()) {
            return "saskaita";
        }

        SetTelNr(saskaita);
        saskaitaService.update(saskaita);

        return "redirect:/list-saskaita";
    }

    @GetMapping("/add-saskaita")
    public String showAddPage(ModelMap model) {
        model.addAttribute("saskaita", new Saskaita());
        return "saskaita";
    }

    @PostMapping("/add-saskaita")
    public String add(@ModelAttribute("saskaita") Saskaita saskaita, BindingResult result) {
        if(result.hasErrors()) {
            return "saskaita";
        }

        SetTelNr(saskaita);
        saskaitaService.add(saskaita);

        return "redirect:/list-saskaita";
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
