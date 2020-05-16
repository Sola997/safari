package com.safari.aukcija.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.safari.aukcija.repository.PredmetRepository;

import model.Kategorija;
import model.Korisnik;
import model.Predmet;
import model.Slika;

@Service
public class PredmetService {

	@Autowired
	PredmetRepository predmetRepository;

	public Predmet addPredmet(Predmet p) {
		return predmetRepository.save(p);
	}

	public Predmet getById(Integer idPredmet) {
		return predmetRepository.findById(idPredmet).orElse(null);
	}

	public List<Predmet> getAll() {
		return predmetRepository.findAll();
	}

	public List<Predmet> getByKategorija(Kategorija kategorija) {
		return predmetRepository.findByKategorija(kategorija);
	}

	public List<Predmet> getZavrseneAukcijeByKorisnik(Korisnik k) {
		List<Predmet> predmeti = this.getByKorisnik(k);
		Date sada = new Date();
		return predmeti.stream().filter(p -> p.getStatus() == (byte) 0 && p.getKrajAukcije().before(sada))
				.collect(Collectors.toList());
	}

	public Slika saveFile(MultipartFile imageFile) {
		String folder = "/photos/";
		try {
			byte[] bytes = imageFile.getBytes();
			Path path = Paths.get(folder + imageFile.getOriginalFilename());
			Files.write(path, bytes);
			Slika slika = new Slika();
			slika.setIme(imageFile.getOriginalFilename());
			slika.setPutanja(folder + imageFile.getOriginalFilename());
			return slika;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Predmet> getByKorisnik(Korisnik korisnik) {
		return predmetRepository.findByKorisnik(korisnik);

	}
}
