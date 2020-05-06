package com.safari.aukcija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safari.aukcija.repository.LicitacijaRepository;

import model.Licitacija;
import model.Predmet;

@Service
public class LicitacijaService {

	@Autowired
	LicitacijaRepository licitacijaRepository;
	
	public Licitacija addLicitacija(Licitacija l) {
		Licitacija ista = licitacijaRepository.findById(l.getId());
		if(ista != null) {
			l.setPobedio(ista.getPobedio());
		}
		if(this.izaberiPobednika(l.getPonuda(), l.getPredmet())) {
			l.setPobedio((byte) 1);
		}
		return licitacijaRepository.save(l);
	}
	
	public boolean izaberiPobednika(int ponuda, Predmet predmet) {
		List<Licitacija> licitacije = licitacijaRepository.findByPredmet(predmet);
		Licitacija pobednik = this.getPobednickaLicitacija(predmet);
		if(licitacije == null || (pobednik != null && pobednik.getPonuda() < ponuda)) {
			pobednik.setPobedio((byte) 0);
			licitacijaRepository.save(pobednik);
			return true;
		}
		// pitati da li treba, jer se nikada nece desiti
		if(pobednik == null) {
			int max = 0;
			Licitacija maxLicitacija = null;
			for(Licitacija l : licitacije) {
				if(l.getPonuda() > max) {
					maxLicitacija = l;
					max = l.getPonuda();
				}
			}
			if(ponuda > max) {
				return true;
			}else {
				maxLicitacija.setPobedio((byte) 1);
				licitacijaRepository.save(maxLicitacija);
			}
		}
		// ^^
		return false;
	}
	
	public List<Licitacija> getAll() {
		 return licitacijaRepository.findAll();
	}

	public Licitacija getPobednickaLicitacija(Predmet predmet) {
		return licitacijaRepository.findByPredmetAndPobedio(predmet,(byte) 1).orElse(null);
	}
}
