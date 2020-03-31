package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.KategorijaService;

import model.Kategorija;

@RestController
@EntityScan("Model")
public class KategorijaController {

	@Autowired
	KategorijaService kr;
	
	@RequestMapping("/getAllKategorije")
	public List<Kategorija> getAllKategorije() {
		return kr.getAll();
	}
	
	@RequestMapping("/saveKategorija")
	public Kategorija saveKategorija(Kategorija k) {
		return kr.addKategorija(k);
	}
}
