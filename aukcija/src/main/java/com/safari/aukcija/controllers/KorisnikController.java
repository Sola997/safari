package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.KorisnikService;

import model.Korisnik;

@RestController
@EntityScan("Model")
public class KorisnikController {

	@Autowired
	KorisnikService ks;
	
	@RequestMapping("/getAllKorinsik")
	public List<Korisnik> getAllKorisnik(){
		return ks.getAll();
	}
	
	@RequestMapping("/saveKorisnik")
	public Korisnik saveKorisnik(Korisnik k) {
		return ks.addKorisnik(k);
	}
	
}
