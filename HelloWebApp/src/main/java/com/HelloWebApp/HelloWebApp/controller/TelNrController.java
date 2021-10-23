package com.HelloWebApp.HelloWebApp.controller;

import com.HelloWebApp.HelloWebApp.service.TelNrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TelNrController {
    @Autowired
    TelNrService telNrService;

    @GetMapping("/list-telNr")
    public String showTelNr(ModelMap model) {
        model.put("telNr", telNrService.findAll());
        return "list-telNr";
    }
}
