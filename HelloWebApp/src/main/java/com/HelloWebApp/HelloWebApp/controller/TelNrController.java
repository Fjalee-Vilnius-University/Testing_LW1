package com.HelloWebApp.HelloWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TelNrController {
    @GetMapping("/telNr-test")
    public String showTelNr() {
        return "test";
    }
}
