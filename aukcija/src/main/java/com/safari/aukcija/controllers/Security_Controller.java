package com.safari.aukcija.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.KorisnikService;

import model.Korisnik;

@RestController
@RequestMapping("security")
@EntityScan("Model")
public class Security_Controller {

	@Autowired
	KorisnikService korisnikService;

	@RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET, produces = "application/json")
	public Korisnik getCurrentUser(Principal p) {
		String username = p.getName();
		Korisnik k = korisnikService.getByUsername(username);
		return k;
	}
}
