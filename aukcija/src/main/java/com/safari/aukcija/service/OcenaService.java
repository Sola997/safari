package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.OcenaRepository;

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
	
}
