package com.safari.aukcija.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.KorisnikService;
import com.safari.aukcija.service.UlogaService;

import model.Korisnik;
import model.Uloga;

@RestController
@RequestMapping("unauth")
@EntityScan("Model")
public class Controller_Unauth {
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	UlogaService ulogaService;

	
	@RequestMapping(value = "/register", method = RequestMethod.POST,  consumes ="application/json", produces = "application/json")
	public Korisnik saveKorisnik(@RequestBody Korisnik korisnik, @RequestParam("idUloga") Integer idUloga) {
		Uloga uloga = ulogaService.getById(idUloga);
		korisnik.setUloga(uloga);
		return korisnikService.addKorisnik(korisnik);
	}
	
	@RequestMapping(value = "loginFaliure", method = RequestMethod.POST, produces = "application/json")
	public boolean loginFaliure() {
		return false;
	}
	
	@RequestMapping(value = "loginSuccess", method = RequestMethod.GET, produces = "application/json")
	public boolean loginSuccess() {
		return true;
	}
}
