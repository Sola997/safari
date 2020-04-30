package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.PorukaRepository;

import model.Korisnik;
import model.Poruka;

@Service
public class PorukaService {

	@Autowired
	PorukaRepository porukaRepository;
	
	public Poruka addPoruka(Poruka p) {
		return porukaRepository.save(p);
	}
	
	public List<Poruka> getByKorisnik(Korisnik k){
		return porukaRepository.getByKorisnik1(k);
	}
	public List<Poruka> getAll(){
		return porukaRepository.findAll();
	}
}
