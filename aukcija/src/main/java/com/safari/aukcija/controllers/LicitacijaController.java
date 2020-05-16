package com.safari.aukcija.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.KorisnikService;
import com.safari.aukcija.service.LicitacijaService;
import com.safari.aukcija.service.PredmetService;

import model.Korisnik;
import model.Licitacija;
import model.LicitacijaPK;
import model.Predmet;

@RestController
@RequestMapping("auth/licitacija")
@EntityScan("Model")
public class LicitacijaController {
	@Autowired
	LicitacijaService licitacijaService;
	
	@Autowired
	PredmetService predmetService;
	
	@Autowired
	KorisnikService korisnikService;


	@RequestMapping(value = "/getAllLicitacija", method = RequestMethod.GET, produces = "application/json")
	public List<Licitacija> getAllLicitacija() {
		return licitacijaService.getAll();
	}
	
	@RequestMapping(value = "/saveLicitacija", method = RequestMethod.POST, produces = "application/json")
	public Licitacija saveLicitacija(@RequestParam("ponuda") Integer ponuda,
			@RequestParam("idPredmet") Integer idPredmet, Principal currentUser) {
		// Vraca null ako predmet ne postoji, pripada ulogovanom korisniku ili je aukcija za taj predmet zavrsena
		Predmet predmet = predmetService.getById(idPredmet);
		Korisnik korisnik = korisnikService.getByUsername(currentUser.getName());
		Date danas = new Date();
		if (predmet == null || korisnik.getIdKorisnik() == predmet.getKorisnik().getIdKorisnik() || danas.after(predmet.getKrajAukcije())) {
			return null;
		}
		Licitacija licitacija = new Licitacija();
		LicitacijaPK pk = new LicitacijaPK();
		pk.setKorisnik_idKorisnik(korisnik.getIdKorisnik());
		pk.setPredmet_idPredmet(predmet.getIdPredmet());
		licitacija.setDatumLicitacije(danas);
		licitacija.setId(pk);
		licitacija.setPonuda(ponuda);
		licitacija.setKorisnik(korisnik);
		licitacija.setPredmet(predmet);
		return licitacijaService.addLicitacija(licitacija);
	}
}
