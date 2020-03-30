package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.repository.KategorijaRepository;

import model.Kategorija;

@RestController
@EntityScan("Model")
public class Controller {
	
	@Autowired
	KategorijaRepository kr;
	
	@RequestMapping("/hello")
	public String sayHi() {
		return "Hello";
	}
	
	@RequestMapping("/getAllKategorije")
	public List<Kategorija> getAllKategorije() {
		return kr.findAll();
	}
}
