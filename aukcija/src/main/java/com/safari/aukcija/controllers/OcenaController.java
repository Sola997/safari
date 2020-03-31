package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.OcenaService;

import model.Ocena;

@RestController
@EntityScan("Model")
public class OcenaController {

	@Autowired
	OcenaService os;
	
	@RequestMapping("/getAllOcena")
	public List<Ocena> getAllOcena(){
		return os.getAll();
	}
	
	@RequestMapping("/saveOcena")
	public Ocena saveOcena(Ocena o) {
		return os.addOcena(o);
	}
}
