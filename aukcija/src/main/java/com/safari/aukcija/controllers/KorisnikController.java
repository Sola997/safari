package com.safari.aukcija.controllers;

import java.security.Principal;
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
@RequestMapping("auth/korisnik")
@EntityScan("Model")
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	PorukaService porukaService;
	
	@Autowired
	PredmetService predmetService;

	@Autowired
	LicitacijaService licitacijaService;

	@RequestMapping(value = "/getAllKorisnik", method = RequestMethod.GET, produces = "application/json")
	public List<Korisnik> getAllKorisnik() {
		return korisnikService.getAll();
	}
	
	@RequestMapping(value = "/getUserByUserName", method = RequestMethod.GET, produces = "application/json")
	public Korisnik getUserByUserName(@RequestParam("userName") String userName) {
		return korisnikService.getByUsername(userName);
	}
	
	@RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET, produces = "application/json")
	public Korisnik getCurrentUser(Principal p) {
		String username = p.getName();
		Korisnik k = korisnikService.getByUsername(username);
		return k;
	}
	
	@RequestMapping(value = "/getInbox", method = RequestMethod.GET)
	public List<Korisnik> getInbox(Principal principal) {
		Korisnik korisnik = korisnikService.getByUsername(principal.getName());
		List<Poruka> poruke = porukaService.getByKorisnik(korisnik);
		// poslednji korisnik sa kojim je razgovarao ide prvi
		List<Korisnik> korisnici = poruke.stream()
				.sorted((p1, p2) -> (p2.getDatum() != null && p1.getDatum() != null)
						? (p2.getDatum().compareTo(p1.getDatum()))
						: (p2.getIdPoruka() - p1.getIdPoruka()))
				.map(p -> (p.getKorisnik1().getIdKorisnik() == korisnik.getIdKorisnik()) ? p.getKorisnik2()
						: p.getKorisnik1())
				.distinct().filter(k -> k.getIdKorisnik() != korisnik.getIdKorisnik()).collect(Collectors.toList());
		return korisnici;
	}
	
	@RequestMapping(value = "/getPobednikaAukcije", method = RequestMethod.GET, produces = "application/json")
	public Korisnik getPobednikaAukcije(@RequestParam("idPredmeta") Integer idPredmet) {
		Predmet predmet = predmetService.getById(idPredmet);
		Licitacija pobednickaLicitacija = licitacijaService.getPobednickaLicitacija(predmet);
		return pobednickaLicitacija == null ? null : pobednickaLicitacija.getKorisnik();
	}
}
