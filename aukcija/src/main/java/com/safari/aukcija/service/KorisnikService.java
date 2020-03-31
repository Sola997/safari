package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.KorisnikRepository;

import model.Korisnik;

@Service
public class KorisnikService {

	@Autowired
	KorisnikRepository kr;
	
	public Korisnik addKorisnik(Korisnik k) {
		return kr.save(k);
	}
	
	public List<Korisnik> getAll() {
		 return kr.findAll();
	}
}
