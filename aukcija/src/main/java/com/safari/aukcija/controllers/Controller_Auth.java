package com.safari.aukcija.controllers;

import java.security.Principal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.repository.PredmetRepository;
import com.safari.aukcija.security.CustomerUserDetailsService;
import com.safari.aukcija.service.KategorijaService;
import com.safari.aukcija.service.KorisnikService;
import com.safari.aukcija.service.LicitacijaService;
import com.safari.aukcija.service.OcenaService;
import com.safari.aukcija.service.PorukaService;
import com.safari.aukcija.service.PredmetService;
import com.safari.aukcija.service.SlikaService;

import model.Kategorija;
import model.Korisnik;
import model.Licitacija;
import model.Ocena;
import model.Poruka;
import model.Predmet;
import model.Slika;

@RestController
@RequestMapping("auth")
@EntityScan("Model")
public class Controller_Auth {

	@Autowired
	CustomerUserDetailsService userDetailsService;
	
	@Autowired
	KorisnikService korisnikService;

	@Autowired
	KategorijaService kategorijaService;

	@Autowired
	OcenaService ocenaService;

	@Autowired
	PorukaService porukaService;

	@Autowired
	PredmetService predmetService;

	@Autowired
	SlikaService slikaService;

	@Autowired
	LicitacijaService licitacijaService;
	
	@Autowired
	PredmetRepository predmetRepository;
	
	@RequestMapping(value = "loginSuccess", method = RequestMethod.GET, produces = "application/json")
	public boolean loginSuccess() {
		return true;
	}
	
	@RequestMapping(value = "loginFaliure", method = RequestMethod.POST, produces = "application/json")
	public boolean loginFaliure() {
		return false;
	}
	
	@RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET, produces = "application/json")
	public Korisnik getCurrentUser(Principal p) {
		String username = p.getName();
		Korisnik k = korisnikService.findByUsername(username);
		return k;
	}

	@RequestMapping(value = "/getAllKorisnik", method = RequestMethod.GET, produces = "application/json")
	public List<Korisnik> getAllKorisnik() {
		return korisnikService.getAll();
	}

	@RequestMapping(value = "/getAllKategorije", method = RequestMethod.GET, produces = "application/json")
	public List<Kategorija> getAllKategorije() {
		return kategorijaService.getAllKategorije();
	}

	@RequestMapping(value = "/getAllOcena", method = RequestMethod.GET, produces = "application/json")
	public List<Ocena> getAllOcena() {
		return ocenaService.getAll();
	}

	@RequestMapping(value = "/getAllPoruka", method = RequestMethod.GET, produces = "application/json")
	public List<Poruka> getAllPoruka() {
		return porukaService.getAll();
	}

	@RequestMapping(value = "/getAllPredmet", method = RequestMethod.GET, produces = "application/json")
	public List<Predmet> getAllPredmet() {
		return predmetService.getAll();
	}

	@RequestMapping(value = "/getAllSlika", method = RequestMethod.GET, produces = "application/json")
	public List<Slika> getAllSlika() {
		return slikaService.getAll();
	}

	@RequestMapping(value = "/getAllLicitacija", method = RequestMethod.GET, produces = "application/json")
	public List<Licitacija> getAllLicitacija() {
		return licitacijaService.getAll();
	}

	@RequestMapping(value = "/saveKategorija" ,method = RequestMethod.POST,  consumes ="application/json", produces = "application/json")
	public Kategorija saveKategorija(@RequestBody Kategorija kategorija) {
		return kategorijaService.addKategorija(kategorija);
	}
	
	@RequestMapping(value = "/getPredmetKategorije" ,method = RequestMethod.POST,  consumes ="application/json", produces = "application/json")
	public List<Predmet> getPredmetKategorije(Kategorija nazivKategorije){
		List<Predmet> predmetKategorije = predmetRepository.predmetPoKategoriji(nazivKategorije);
		return predmetKategorije;
	}
}
