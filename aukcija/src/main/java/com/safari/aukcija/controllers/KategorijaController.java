package com.safari.aukcija.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.KategorijaService;

import model.Kategorija;


@RestController
@EntityScan("Model")
public class KategorijaController {

	@Autowired
	KategorijaService korisnikService;
	
	@RequestMapping("/getAllKategorije")
	public List<Kategorija> getAllKategorije() {
		return korisnikService.getAll();
	}
	
	@RequestMapping("/saveKategorija")
	public Kategorija saveKategorija(HttpServletRequest request) {
		Kategorija k = new Kategorija();
		String nazivKategorije = request.getParameter("nazivKategorije");
		k.setNazivKategorije(nazivKategorije);
<<<<<<< HEAD
		return korisnikService.addKategorija(k);
=======
		return kr.addKategorija(k);
>>>>>>> 091bde4245aa272c38577657a64c8ae8c491a990
	}
}
