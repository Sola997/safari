package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.KategorijaService;

import model.Kategorija;

@RestController
@RequestMapping("auth/katogorija")
@EntityScan("Model")
public class KategorijaController {

	@Autowired
	KategorijaService kategorijaService;

	@RequestMapping(value = "/getAllKategorije", method = RequestMethod.GET, produces = "application/json")
	public List<Kategorija> getAllKategorije() {
		return kategorijaService.getAllKategorije();
	}
	
	@RequestMapping(value = "/saveKategorija", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Kategorija saveKategorija(@RequestBody Kategorija kategorija) {
		return kategorijaService.addKategorija(kategorija);
	}
}
