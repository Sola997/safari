package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.LicitacijaService;

import model.Licitacija;

@RestController
@EntityScan("Model")
public class LicitacijaController {
	
	@Autowired
	LicitacijaService ls;
	
	@RequestMapping("/getAllLicitacija")
	public List<Licitacija> getAllLicitacija(){
		return ls.getAll();
	}
	
	@RequestMapping("/saveKorisnik")
	public Licitacija saveLicitacija(Licitacija l) {
		return ls.addLicitacija(l);
	}
	
}
