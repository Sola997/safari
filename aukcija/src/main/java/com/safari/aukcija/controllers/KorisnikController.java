package com.safari.aukcija.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.repository.KorisnikRepository;
import com.safari.aukcija.service.KorisnikService;

import model.Korisnik;

@RestController
@EntityScan("Model")
public class KorisnikController {

	@Autowired
<<<<<<< HEAD
	KorisnikService korisnikService;
	
	@RequestMapping("/getAllKorinsik")
	public List<Korisnik> getAllKorisnik(){
		return korisnikService.getAll();
=======
	KorisnikService ks;

	@RequestMapping("/getAllKorisnik")
	public List<Korisnik> getAllKorisnik() {
		return ks.getAll();
>>>>>>> 091bde4245aa272c38577657a64c8ae8c491a990
	}

	@RequestMapping("/saveKorisnik")
	public Korisnik saveKorisnik(HttpServletRequest request) {
<<<<<<< HEAD
		String e_mail = request.getParameter("e_mail");
=======
		String email = request.getParameter("email");
>>>>>>> 091bde4245aa272c38577657a64c8ae8c491a990
		String imeKorisnika = request.getParameter("imeKorisnika");
		String password = request.getParameter("password");
		String prezimeKorisnika = request.getParameter("prezimeKorisnika");
		String username = request.getParameter("username");
		Korisnik k = new Korisnik();
<<<<<<< HEAD
		k.setE_mail(e_mail);
=======
		k.setE_mail(email);
>>>>>>> 091bde4245aa272c38577657a64c8ae8c491a990
		k.setImeKorisnika(imeKorisnika);
		k.setPassword(password);
		k.setPrezimeKorisnika(prezimeKorisnika);
		k.setUsername(username);
<<<<<<< HEAD
		return korisnikService.addKorisnik(k);
=======
		return ks.addKorisnik(k);
>>>>>>> 091bde4245aa272c38577657a64c8ae8c491a990
	}

}
