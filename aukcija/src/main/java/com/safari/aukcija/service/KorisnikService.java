package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.KorisnikRepository;

import model.Korisnik;


@Service
public class KorisnikService {

	@Autowired
	KorisnikRepository kategorijaRepository;
	
	
	public Korisnik addKorisnik(Korisnik k) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		k.setPassword(passwordEncoder.encode(k.getPassword()));
		return kategorijaRepository.save(k);
	}
	
	public List<Korisnik> getAll() {
		 return kategorijaRepository.findAll();
	}

	public Korisnik findByUsername(String username) {
		return kategorijaRepository.findByUsername(username);
	}
	
}
