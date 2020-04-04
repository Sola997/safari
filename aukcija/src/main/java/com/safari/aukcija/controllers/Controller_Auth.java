package com.safari.aukcija.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.KategorijaService;
import com.safari.aukcija.service.KorisnikService;

import model.Kategorija;
import model.Korisnik;

@RestController
@RequestMapping("auth")
@EntityScan("Model")
public class Controller_Auth {
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	KategorijaService kategorijaService;
	
	@RequestMapping("/getAllKorisnik")
	public List<Korisnik> getAllKorisnik(){
		return korisnikService.getAll();
	}
	
	@RequestMapping("/getAllKategorije")
	public List<Kategorija> getAllKategorije() {
		return kategorijaService.getAllKategorije();
	}
	
	@RequestMapping("/saveKategorija")
	public Kategorija saveKategorija(HttpServletRequest request) {
		Kategorija k = new Kategorija();
		String nazivKategorije = request.getParameter("nazivKategorije");
		k.setNazivKategorije(nazivKategorije);
		return kategorijaService.addKategorija(k);

	}
}
