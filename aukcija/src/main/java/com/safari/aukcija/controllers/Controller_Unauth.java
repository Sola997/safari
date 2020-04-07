package com.safari.aukcija.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.KorisnikService;

import model.Korisnik;

@RestController
@RequestMapping("unauth")
@EntityScan("Model")
public class Controller_Unauth {
	
	@Autowired
	KorisnikService korisnikService;

	
	@RequestMapping(value = "/register", method = RequestMethod.POST,  consumes ="application/json", produces = "application/json")
	public Korisnik saveKorisnik(@RequestBody Korisnik korisnik) {

		return korisnikService.addKorisnik(korisnik);
	}
}
