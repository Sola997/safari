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
	
	@PostMapping("/saveKorisnik")
	public Licitacija saveLicitacija(Licitacija l) {
		Licitacija licitacija= licitacijaService.addLicitacija(l);
		return licitacija;
		
		
	}
	
}
