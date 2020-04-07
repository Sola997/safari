package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping("/getAllKorisnik")
	public List<Korisnik> getAllKorisnik() {
		return korisnikService.getAll();
	}

	@RequestMapping("/getAllKategorije")
	public List<Kategorija> getAllKategorije() {
		return kategorijaService.getAllKategorije();
	}

	@RequestMapping("/getAllOcena")
	public List<Ocena> getAllOcena() {
		return ocenaService.getAll();
	}

	@RequestMapping("/getAllPoruka")
	public List<Poruka> getAllPoruka() {
		return porukaService.getAll();
	}

	@RequestMapping("/getAllPredmet")
	public List<Predmet> getAllPredmet() {
		return predmetService.getAll();
	}

	@RequestMapping("/getAllSlika")
	public List<Slika> getAllSlika() {
		return slikaService.getAll();
	}

	@RequestMapping("/getAllLicitacija")
	public List<Licitacija> getAllLicitacija() {
		return licitacijaService.getAll();
	}

	@RequestMapping(value = "/saveKategorija" ,method = RequestMethod.POST,  consumes ="application/json", produces = "application/json")
	public Kategorija saveKategorija(@RequestBody Kategorija kategorija) {
		return kategorijaService.addKategorija(kategorija);
	}
}
