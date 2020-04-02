package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.LicitacijaService;

import model.Licitacija;

@RestController
@EntityScan("Model")
public class LicitacijaController {
	
	@Autowired
	LicitacijaService licitacijaService;
	
	@RequestMapping("/getAllLicitacija")
	public List<Licitacija> getAllLicitacija(){
		return licitacijaService.getAll();
	}
	
<<<<<<< HEAD
	@PostMapping("/saveKorisnik")
=======
	@RequestMapping("/saveLicitacija")
>>>>>>> 091bde4245aa272c38577657a64c8ae8c491a990
	public Licitacija saveLicitacija(Licitacija l) {
		Licitacija licitacija= licitacijaService.addLicitacija(l);
		return licitacija;
		
		
	}
	
}
