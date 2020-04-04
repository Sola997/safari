package com.safari.aukcija.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.safari.aukcija.service.KorisnikService;

import model.Korisnik;

@RestController
@RequestMapping("unauth")
@EntityScan("Model")
public class Controller_Unauth {
	
	@Autowired
	KorisnikService korisnikService;

	
	@RequestMapping("/saveKorisnik")
	public Korisnik saveKorisnik(HttpServletRequest request) {
		String e_mail = request.getParameter("e_mail");
		String imeKorisnika = request.getParameter("imeKorisnika");
		String password = request.getParameter("password");
		String prezimeKorisnika = request.getParameter("prezimeKorisnika");
		String username = request.getParameter("username");
		Korisnik k = new Korisnik();
		k.setE_mail(e_mail);
		k.setImeKorisnika(imeKorisnika);
		k.setPassword(password);
		k.setPrezimeKorisnika(prezimeKorisnika);
		k.setUsername(username);

		return korisnikService.addKorisnik(k);
	}
}
