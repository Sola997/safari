package com.safari.aukcija.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.KorisnikRepository;

import model.Korisnik;


@Service
public class KorisnikService {

	@Autowired
	KorisnikRepository korisnikRepository;
	@Autowired
	OcenaService ocenaService;
	
	
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

	public List<Korisnik> get5Najboljih() {
		List<Korisnik> svi = korisnikRepository.findAll();
		return svi.stream().sorted((k1, k2) -> Double.compare(ocenaService.prosek(k2), ocenaService.prosek(k1))).limit(5).collect(Collectors.toList());
	}
	
}
