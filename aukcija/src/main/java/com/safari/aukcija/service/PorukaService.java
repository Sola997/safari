package com.safari.aukcija.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.PorukaRepository;

import model.Korisnik;
import model.Licitacija;
import model.Poruka;
import model.Predmet;

@Service
public class PorukaService {

	@Autowired
	PorukaRepository porukaRepository;

	public Poruka addPoruka(Poruka p) {
		return porukaRepository.save(p);
	}

	public void addNotification(Korisnik k, Predmet p, Licitacija pobednickaLicitacija) {
		Poruka poruka = new Poruka();
		poruka.setKorisnik1(k);
		poruka.setKorisnik2(k);
		poruka.setDatum(new Date());
		poruka.setTekstPoruke(
				"Vasa licitacija za predmet: " + p + " je zavrsena. Pobednicka licitacija: " + pobednickaLicitacija);
		this.addPoruka(poruka);
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
		List<Poruka> sent = porukaRepository.findByKorisnik1AndKorisnik2(k1, k2);
		List<Poruka> inbox = porukaRepository.findByKorisnik1AndKorisnik2(k2, k1);
		if (sent != null) {
			sent.addAll(inbox);
		} else {
			return inbox;
		}
		return sent;
	}

	public List<Poruka> getNotifications(Korisnik k) {
		return porukaRepository.findByKorisnik1AndKorisnik2(k, k);
	}

	public List<Poruka> getAll() {
		return porukaRepository.findAll();
	}
}
