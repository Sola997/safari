package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.LicitacijaRepository;

import model.Licitacija;

@Service
public class LicitacijaService {

	@Autowired
	LicitacijaRepository lr;
	
	public Licitacija addLicitacija(Licitacija l) {
		return lr.save(l);
	}
	
	public List<Licitacija> getAll() {
		 return lr.findAll();
	}
}
