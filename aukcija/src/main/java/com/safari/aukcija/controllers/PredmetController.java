package com.safari.aukcija.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.KategorijaService;
import com.safari.aukcija.service.KorisnikService;
import com.safari.aukcija.service.PredmetService;

import model.Kategorija;
import model.Korisnik;
import model.Predmet;

@RestController
@RequestMapping("auth/predmet")
@EntityScan("Model")
public class PredmetController {

	@Autowired
	PredmetService predmetService;

	@Autowired
	KategorijaService kategorijaService;
	
	@Autowired
	KorisnikService korisnikService;

	@RequestMapping(value = "/getAllPredmet", method = RequestMethod.GET, produces = "application/json")
	public List<Predmet> getAllPredmet() {
		return predmetService.getAll();
	}

	@RequestMapping(value = "/getPredmetsByKategorija", method = RequestMethod.GET, produces = "application/json")
	public List<Predmet> getPredmetsByKategorija(@RequestParam("idKategorija") Integer idKategorija) {
		Kategorija kategorija = kategorijaService.getById(idKategorija);
		return predmetService.getByKategorija(kategorija);
	}
	
	@RequestMapping(value = "/savePredmet", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Predmet savePredmet(@RequestBody Predmet predmet, @RequestParam("idKategorija") Integer idKategorija,
			Principal currentUser) {
		Kategorija kategorija = kategorijaService.getById(idKategorija);
		if (kategorija != null) {
			predmet.setKategorija(kategorija);
		}
		Korisnik korisnik = korisnikService.getByUsername(currentUser.getName());
		predmet.setKorisnik(korisnik);
		return predmetService.addPredmet(predmet);
	}
}
