package com.safari.aukcija.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.repository.OcenaRepository;
import com.safari.aukcija.service.KorisnikService;
import com.safari.aukcija.service.OcenaService;

import model.Korisnik;
import model.Ocena;

@RestController
@RequestMapping("auth/ocena")
@EntityScan("Model")
public class OcenaController {

	@Autowired
	OcenaService ocenaService;
	
	@Autowired
	OcenaRepository ocenaRepository;
	
	@Autowired
	KorisnikService korisnikService;

	@RequestMapping(value = "/getAllOcena", method = RequestMethod.GET, produces = "application/json")
	public List<Ocena> getAllOcena() {
		return ocenaService.getAll();
	}
	
	@RequestMapping(value = "/getOcenaByID", method = RequestMethod.GET)
	public List<Ocena> getOcenaByID(@RequestParam("idKorisnik") Integer idKorisnik) {
		return ocenaRepository.getByKorisnik(idKorisnik);
	}
	
	@RequestMapping(value = "/getOcenaByUsername", method = RequestMethod.GET)
	public List<Ocena> getOcenaByUsername(@RequestParam("username") String username) {
		return ocenaRepository.getByUsername(username);
	}
	
	@RequestMapping(value = "/getOcenaByCurrentUser", method = RequestMethod.GET)
	public List<Ocena> getOcenaByCurrentUser(Principal currUser) {
		return ocenaRepository.getByUsername(currUser.getName());
	}
	
	@RequestMapping(value = "/saveOcena", method = RequestMethod.POST, produces = "application/json")
	public Ocena saveKomnetarIOcenu(@RequestParam("ocena") Integer ocena, @RequestParam("komentar") String komentar,
			@RequestParam("idKorisnik") Integer idKorisnik, Principal p) {
		Korisnik korisnik = korisnikService.getById(idKorisnik);
		Korisnik currentUser = korisnikService.getByUsername(p.getName());
		if (korisnik == null || idKorisnik == currentUser.getIdKorisnik()) {
			return null;
		}
		Ocena o = new Ocena();
		o.setKomentar(komentar);
		o.setOcena(ocena);
		o.setKorisnik(korisnik);
		return ocenaService.addOcena(o);

	}
	
	@RequestMapping(value = "prosekOcena", method = RequestMethod.GET, produces = "application/json")
	public double prosekOcena(Principal currUser) {
		Korisnik korisnik= korisnikService.getByUsername(currUser.getName());
		return ocenaService.prosek(korisnik);
		
	}
}
