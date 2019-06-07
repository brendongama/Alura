package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	private String index() {
		System.out.println("Entrando na Home da casa do Código");
		return "home";  
	}
}
