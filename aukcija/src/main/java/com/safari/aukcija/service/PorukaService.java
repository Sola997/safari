package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.PorukaRepository;

import model.Poruka;

@Service
public class PorukaService {

	@Autowired
	PorukaRepository pr;
	
	public Poruka addPoruka(Poruka p) {
		return pr.save(p);
	}
	
	public List<Poruka> getAll (){
		return pr.findAll();
	}
}
