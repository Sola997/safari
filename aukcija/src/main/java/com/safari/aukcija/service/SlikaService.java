package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.SlikaRepository;

import model.Slika;

@Service
public class SlikaService {

	@Autowired
	SlikaRepository sr;
	
	public Slika addSlika(Slika s) {
		return sr.save(s);
	}
	
	public List<Slika> getAll(){
		return sr.findAll();
	}
	
}
