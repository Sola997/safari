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
		return korisnikService.addKategorija(k);
	}
}
