package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.PorukaService;

import model.Poruka;

@RestController
@EntityScan("Model")
public class PorukaController {

	@Autowired
	PorukaService ps;
	
	@RequestMapping("/getAllPoruka")
	public List<Poruka> getAllPoruka(){
		return ps.getAll();
	}
	
	@RequestMapping("/savePoruka")
	public Poruka savePoruka(Poruka p) {
		return ps.addPoruka(p);
	}
	
}	
