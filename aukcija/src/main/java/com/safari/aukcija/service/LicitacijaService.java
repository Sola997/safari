package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.LicitacijaRepository;

import model.Licitacija;

@Service
public class LicitacijaService {

	@Autowired
	LicitacijaRepository licitacijaRepository;
	
	public Licitacija addLicitacija(Licitacija l) {
		return licitacijaRepository.save(l);
	}
	
	public List<Licitacija> getAll() {
		 return licitacijaRepository.findAll();
	}
}
