package com.safari.aukcija.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.UlogaRepository;

import model.Uloga;

@Service
public class UlogaService {
	@Autowired
	UlogaRepository ulogaRepository;
	public Uloga getById(Integer idUloga) {
		// TODO Auto-generated method stub
		return ulogaRepository.findById(idUloga).orElse(null);
	}
}
