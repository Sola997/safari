package com.safari.aukcija.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.safari.aukcija.service.KategorijaService;
import com.safari.aukcija.service.KorisnikService;
import com.safari.aukcija.service.LicitacijaService;
import com.safari.aukcija.service.OcenaService;
import com.safari.aukcija.service.PorukaService;
import com.safari.aukcija.service.PredmetService;
import com.safari.aukcija.service.SlikaService;

import model.Kategorija;
import model.Korisnik;
import model.Licitacija;
import model.LicitacijaPK;
import model.Ocena;
import model.Poruka;
import model.Predmet;
import model.Slika;

@RestController
@RequestMapping("auth")
@EntityScan("Model")
public class Controller_Auth {

	@Autowired
	KorisnikService korisnikService;

	@Autowired
	KategorijaService kategorijaService;

	@Autowired
	OcenaService ocenaService;

	@Autowired
	PorukaService porukaService;

	@Autowired
	PredmetService predmetService;

	@Autowired
	SlikaService slikaService;

	@Autowired
	LicitacijaService licitacijaService;

	// getAll

	@RequestMapping(value = "/getAllKorisnik", method = RequestMethod.GET, produces = "application/json")
	public List<Korisnik> getAllKorisnik() {
		return korisnikService.getAll();
	}

	@RequestMapping(value = "/getAllKategorije", method = RequestMethod.GET, produces = "application/json")
	public List<Kategorija> getAllKategorije() {
		return kategorijaService.getAllKategorije();
	}

	@RequestMapping(value = "/getAllOcena", method = RequestMethod.GET, produces = "application/json")
	public List<Ocena> getAllOcena() {
		return ocenaService.getAll();
	}

	@RequestMapping(value = "/getAllPoruka", method = RequestMethod.GET, produces = "application/json")
	public List<Poruka> getAllPoruka() {
		return porukaService.getAll();
	}

	@RequestMapping(value = "/getAllPredmet", method = RequestMethod.GET, produces = "application/json")
	public List<Predmet> getAllPredmet() {
		return predmetService.getAll();
	}

	@RequestMapping(value = "/getAllSlika", method = RequestMethod.GET, produces = "application/json")
	public List<Slika> getAllSlika() {
		return slikaService.getAll();
	}

	@RequestMapping(value = "/getAllLicitacija", method = RequestMethod.GET, produces = "application/json")
	public List<Licitacija> getAllLicitacija() {
		return licitacijaService.getAll();
	}

	// getBy

	@RequestMapping(value = "/getPredmetsByKategorija", method = RequestMethod.GET, produces = "application/json")
	public List<Predmet> getPredmetsByKategorija(@RequestParam("idKategorija") Integer idKategorija) {
		Kategorija kategorija = kategorijaService.getById(idKategorija);
		return predmetService.getByKategorija(kategorija);
	}

	// save

	@RequestMapping(value = "/saveKategorija", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Kategorija saveKategorija(@RequestBody Kategorija kategorija) {
		return kategorijaService.addKategorija(kategorija);
	}

	@RequestMapping(value = "/savePredmet", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Predmet savePredmet(@RequestBody Predmet predmet, @RequestParam("idKategorija") Integer idKategorija,
			Principal currentUser) {
		Kategorija kategorija = kategorijaService.getById(idKategorija);
		if (kategorija != null) {
			predmet.setKategorija(kategorija);
		}
		Korisnik korisnik = korisnikService.findByUsername(currentUser.getName());
		predmet.setKorisnik(korisnik);
		return predmetService.addPredmet(predmet);
	}
	
	@RequestMapping(value = "/saveSlika", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Slika saveSlika(@RequestParam("idPredmet") Integer idPredmet, @RequestParam("imageFile") MultipartFile imageFile) {
		if (imageFile != null) {
			Slika slika = predmetService.saveFile(imageFile);
			if (slika != null) {
				slika.setPredmet(predmetService.getById(idPredmet));
				return slikaService.addSlika(slika);
			}
		}
		return null;
	}

	@RequestMapping(value = "/saveLicitacija", method = RequestMethod.POST, produces = "application/json")
	public Licitacija saveLicitacija(@RequestParam("ponuda") Integer ponuda,
			@RequestParam("idPredmet") Integer idPredmet, Principal currentUser) {
		// Vraca null ako predmet ne postoji ili pripada ulogovanom korisniku
		Predmet predmet = predmetService.getById(idPredmet);
		if (predmet == null) {
			return null;
		}
		Korisnik korisnik = korisnikService.findByUsername(currentUser.getName());
		if (korisnik.getIdKorisnik() == predmet.getKorisnik().getIdKorisnik()) {
			return null;
		}
		Licitacija licitacija = new Licitacija();
		LicitacijaPK pk = new LicitacijaPK();
		pk.setKorisnik_idKorisnik(korisnik.getIdKorisnik());
		pk.setPredmet_idPredmet(predmet.getIdPredmet());
		licitacija.setDatumLicitacije();
		licitacija.setId(pk);
		licitacija.setPonuda(ponuda);
		licitacija.setPobedio((byte) 0);
		licitacija.setKorisnik(korisnik);
		licitacija.setPredmet(predmet);
		return licitacijaService.addLicitacija(licitacija);
	}

	@RequestMapping(value = "/saveOcena", method = RequestMethod.POST, produces = "application/json")
	public Ocena saveKomnetarIOcenu(@RequestParam("ocena") Integer ocena, @RequestParam("komentar") String komentar,
			@RequestParam("idKorisnik") Integer idKorisnik) {
		Korisnik korisnik = korisnikService.findById(idKorisnik);
		if (korisnik == null) {
			return null;
		}
		Ocena o = new Ocena();
		o.setKomentar(komentar);
		o.setOcena(ocena);
		o.setKorisnik(korisnik);
		return ocenaService.addOcena(o);

	}

	@RequestMapping(value = "/getUserByUserName", method = RequestMethod.GET, produces = "application/json")
	public Korisnik getUserByUserName(@RequestParam("userName") String userName) {
		return korisnikService.findByUsername(userName);
	}
}
