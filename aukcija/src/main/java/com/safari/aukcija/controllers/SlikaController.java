package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safari.aukcija.service.SlikaService;

import model.Slika;

@RestController
@EntityScan("Model")
public class SlikaController {

	@Autowired
	SlikaService ss;
	
	@RequestMapping("/getAllSlika")
	public List<Slika> getAllSlika(){
		return ss.getAll();
	}
	
	@RequestMapping("/saveSlika")
	public Slika saveSlika(Slika s) {
		return ss.addSlika(s);
	}
}
