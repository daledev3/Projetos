package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


	    @GetMapping("/")
	    public String home() {
	    	return "Você está logado e a aplicação está funcionando!";

	    }
}
