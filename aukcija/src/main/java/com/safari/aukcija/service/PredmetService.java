package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.PredmetRepository;

import model.Predmet;

@Service
public class PredmetService {

	@Autowired
	PredmetRepository pr;
	
	public Predmet addPredmet(Predmet p) {
		return pr.save(p);
	}
	
	
	public List<Predmet> getAll(){
		return pr.findAll();
	}
}
