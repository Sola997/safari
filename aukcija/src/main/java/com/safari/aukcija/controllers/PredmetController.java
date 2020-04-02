package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.PredmetService;

import model.Predmet;



@RestController
@EntityScan("Model")
public class PredmetController {

	@Autowired
	PredmetService predmetService;
	
	@RequestMapping("/getAllPredmet")
	public List<Predmet> getAllPredmet(){
		return predmetService.getAll();
	}
	
	@RequestMapping("/savePredmet")
	public Predmet savePoruka(Predmet p) {
		Predmet predmet = predmetService.addPredmet(p);
		return predmet;
	}
}
