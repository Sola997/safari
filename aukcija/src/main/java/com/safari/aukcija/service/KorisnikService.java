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
	KorisnikRepository kr;
	
	public Korisnik addKorisnik(Korisnik k) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		k.setPassword(passwordEncoder.encode(k.getPassword()));
		return kr.save(k);
	}
	
	public List<Korisnik> getAll() {
		 return kr.findAll();
	}

	public Korisnik findByUsername(String username) {
		// TODO Auto-generated method stub
		return kr.findByUsername(username);
	}
	
}
