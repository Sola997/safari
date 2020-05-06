package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.OcenaRepository;

import model.Korisnik;
import model.Ocena;

@Service
public class OcenaService {

	@Autowired
	OcenaRepository ocenaRepository;
	
	public Ocena addOcena(Ocena o) {
		return ocenaRepository.save(o);
	}
	
	public List<Ocena> getAll() {
		 return ocenaRepository.findAll();
	}
	
	public double prosek(Korisnik korisnik) {
		List<Ocena> ocene = this.getOcenaByKorisnik(korisnik);
		return ocene.stream().mapToInt(o -> o.getOcena()).average().orElse(0);
	}

	private List<Ocena> getOcenaByKorisnik(Korisnik korisnik) {
		return ocenaRepository.findByKorisnik(korisnik);
	}
}
