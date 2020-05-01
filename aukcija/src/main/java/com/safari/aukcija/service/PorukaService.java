package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.PorukaRepository;

import model.Korisnik;
import model.Poruka;

@Service
public class PorukaService {

	@Autowired
	PorukaRepository porukaRepository;

	public Poruka addPoruka(Poruka p) {
		return porukaRepository.save(p);
	}

	public List<Poruka> getByKorisnik(Korisnik k) {
		List<Poruka> sent = porukaRepository.getByKorisnik1(k);
		List<Poruka> inbox = porukaRepository.getByKorisnik2(k);
		if (sent != null) {
			sent.addAll(inbox);
		} else {
			return inbox;
		}
		return sent;
	}

	public List<Poruka> getPorukeWithKorisnik(Korisnik k1, Korisnik k2) {
		List<Poruka> sent = porukaRepository.findByKorisnik1AndKorisnik2(k1,k2);
		List<Poruka> inbox = porukaRepository.findByKorisnik1AndKorisnik2(k2,k1);
		if (sent != null) {
			sent.addAll(inbox);
		} else {
			return inbox;
		}
		return sent;
	}

	public List<Poruka> getAll() {
		return porukaRepository.findAll();
	}
}
