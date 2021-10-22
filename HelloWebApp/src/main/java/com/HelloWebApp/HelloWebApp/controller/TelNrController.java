package com.HelloWebApp.HelloWebApp.controller;

import com.HelloWebApp.HelloWebApp.service.TelNrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelNrController {

    @GetMapping("/telNr-test")
    public String showTelNr() {
        return "test";
    }
}
