package com.safari.aukcija.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.safari.aukcija.service.PredmetService;
import com.safari.aukcija.service.SlikaService;

import model.Slika;

@RestController
@RequestMapping("auth/slika")
@EntityScan("Model")
public class SlikaController {

	@Autowired
	SlikaService slikaService;
	
	@Autowired
	PredmetService predmetService;

	@RequestMapping(value = "/getAllSlika", method = RequestMethod.GET, produces = "application/json")
	public List<Slika> getAllSlika() {
		return slikaService.getAll();
	}
	
	@RequestMapping(value = "/saveSlika", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Slika saveSlika(@RequestParam("idPredmet") Integer idPredmet,
			@RequestParam("imageFile") MultipartFile imageFile) {
		if (imageFile != null) {
			Slika slika = predmetService.saveFile(imageFile);
			if (slika != null) {
				slika.setPredmet(predmetService.getById(idPredmet));
				return slikaService.addSlika(slika);
			}
		}
		return null;
	}
}
