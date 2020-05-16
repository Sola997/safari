package com.safari.aukcija.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.KorisnikService;
import com.safari.aukcija.service.LicitacijaService;
import com.safari.aukcija.service.PorukaService;
import com.safari.aukcija.service.PredmetService;

import model.Korisnik;
import model.Licitacija;
import model.Poruka;
import model.Predmet;

@RestController
@RequestMapping("auth/poruka")
@EntityScan("Model")
public class PorukaController {

	@Autowired
	PorukaService porukaService;

	@Autowired
	KorisnikService korisnikService;

	@Autowired
	PredmetService predmetService;

	@Autowired
	LicitacijaService licitacijaService;

	@RequestMapping(value = "/getAllPoruka", method = RequestMethod.GET, produces = "application/json")
	public List<Poruka> getAllPoruka() {
		return porukaService.getAll();
	}

	@RequestMapping(value = "/getPorukeWithKorisnik", method = RequestMethod.GET)
	public List<Poruka> getPorukeByCurrentUser(@RequestParam("idKorisnik") Integer idKorisnik, Principal principal) {
		Korisnik k1 = korisnikService.getByUsername(principal.getName());
		Korisnik k2 = korisnikService.getById(idKorisnik);
		// najstarija poruka prva
		return porukaService.getPorukeWithKorisnik(k1, k2).stream()
				.sorted((p1, p2) -> p1.getDatum().compareTo(p2.getDatum())).collect(Collectors.toList());
	}

	@RequestMapping(value = "/getNotifications", method = RequestMethod.GET, produces = "application/json")
	public List<Poruka> getNotifications(Principal principal) {
		Korisnik korisnik = korisnikService.getByUsername(principal.getName());
		List<Predmet> predmeti = predmetService.getNoveZavrseneAukcijeByKorisnik(korisnik);
		for (Predmet p : predmeti) {
			p.setStatus((byte) 1);
			Licitacija pobednickaLicitacija = licitacijaService.getPobednickaLicitacija(p);
			porukaService.addNotification(korisnik, p, pobednickaLicitacija);
		}
		return porukaService.getNotifications(korisnik);
	}

	@RequestMapping(value = "savePoruka", method = RequestMethod.POST, produces = "application/json")
	public Poruka savePoruka(@RequestParam("idKorisnik") Integer idKorisnik2, @RequestParam("text") String text,
			Principal principal) {
		Poruka poruka = new Poruka();
		poruka.setDatum(new Date());
		poruka.setTekstPoruke(text);
		Korisnik korisnik1 = korisnikService.getByUsername(principal.getName());
		Korisnik korisnik2 = korisnikService.getById(idKorisnik2);
		poruka.setKorisnik1(korisnik1);
		poruka.setKorisnik2(korisnik2);
		return porukaService.addPoruka(poruka);
	}
}
