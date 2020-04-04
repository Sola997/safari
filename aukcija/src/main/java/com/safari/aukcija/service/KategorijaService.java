package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.KategorijaRepository;

import model.Kategorija;

@Service
public class KategorijaService {
	
	@Autowired
	KategorijaRepository kr;
	
	public Kategorija addKategorija(Kategorija k) {
		return kr.save(k);
	}
	
	public List<Kategorija> getAllKategorije() {
		 return kr.findAll();
	}
}
