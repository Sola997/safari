package com.safari.aukcija.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping("/getAllKorisnik")
	public List<Korisnik> getAllKorisnik() {
		return ks.getAll();
	}

	@RequestMapping("/saveKorisnik")
	public Korisnik saveKorisnik(HttpServletRequest request) {
		String email = request.getParameter("email");
		String imeKorisnika = request.getParameter("imeKorisnika");
		String password = request.getParameter("password");
		String prezimeKorisnika = request.getParameter("prezimeKorisnika");
		String username = request.getParameter("username");
		Korisnik k = new Korisnik();
		k.setE_mail(email);
		k.setImeKorisnika(imeKorisnika);
		k.setPassword(password);
		k.setPrezimeKorisnika(prezimeKorisnika);
		k.setUsername(username);
		return ks.addKorisnik(k);
	}

}
