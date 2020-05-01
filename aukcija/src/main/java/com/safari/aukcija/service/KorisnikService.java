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
	KorisnikRepository korisnikRepository;
	
	
	public Korisnik addKorisnik(Korisnik k) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		k.setPassword(passwordEncoder.encode(k.getPassword()));
		return korisnikRepository.save(k);
	}
	
	public List<Korisnik> getAll() {
		 return korisnikRepository.findAll();
	}
	
	public Korisnik getById(Integer idKorisnik) {
		// TODO Auto-generated method stub
		return korisnikRepository.findById(idKorisnik).orElse(null);
	}
	
	public Korisnik getByUsername(String username) {
		return korisnikRepository.findByUsername(username);
	}
	
}
