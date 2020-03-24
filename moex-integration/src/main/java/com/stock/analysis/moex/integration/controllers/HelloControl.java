package com.stock.analysis.moex.integration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloControl {
    @GetMapping("/")
    public String hello(){
        return "helloPage";
    }
}
