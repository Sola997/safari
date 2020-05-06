package com.safari.aukcija.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.safari.aukcija.repository.OcenaRepository;
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

	@Autowired
	OcenaRepository ocenaRepository;

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

	@RequestMapping(value = "/getUserByUserName", method = RequestMethod.GET, produces = "application/json")
	public Korisnik getUserByUserName(@RequestParam("userName") String userName) {
		return korisnikService.getByUsername(userName);
	}

	@RequestMapping(value = "/getOcenaByID", method = RequestMethod.GET)
	public List<Ocena> getOcenaByID(@RequestParam("idKorisnik") Integer idKorisnik) {
		return ocenaRepository.getByKorisnik(idKorisnik);
	}

	@RequestMapping(value = "/getOcenaByUsername", method = RequestMethod.GET)
	public List<Ocena> getOcenaByUsername(@RequestParam("username") String username) {
		return ocenaRepository.getByUsername(username);
	}

	@RequestMapping(value = "/getOcenaByCurrentUser", method = RequestMethod.GET)
	public List<Ocena> getOcenaByCurrentUser(Principal currUser) {
		return ocenaRepository.getByUsername(currUser.getName());
	}
	
	@RequestMapping(value = "/getPorukeWithKorisnik", method = RequestMethod.GET)
	public List<Poruka> getPorukeByCurrentUser(@RequestParam("idKorisnik") Integer idKorisnik, Principal principal) {
		Korisnik k1 = korisnikService.getByUsername(principal.getName());
		Korisnik k2 = korisnikService.getById(idKorisnik);
		// najstarija poruka prva
		return porukaService.getPorukeWithKorisnik(k1,k2)
				.stream()
				.sorted((p1,p2)->p1.getDatum().compareTo(p2.getDatum()))
				.collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/getInbox", method = RequestMethod.GET)
	public List<Korisnik> getInbox(Principal principal){
		Korisnik korisnik = korisnikService.getByUsername(principal.getName());
		List<Poruka> poruke = porukaService.getByKorisnik(korisnik);
		// poslednji korisnik sa kojim je razgovarao ide prvi
		List<Korisnik> korisnici = poruke.stream()
				.sorted((p1,p2)->(p2.getDatum() != null && p1.getDatum() != null) ? (p2.getDatum().compareTo(p1.getDatum())) : (p2.getIdPoruka() - p1.getIdPoruka()))
				.map(p -> (p.getKorisnik1().getIdKorisnik() == korisnik.getIdKorisnik())? p.getKorisnik2() : p.getKorisnik1())
				.distinct()
				.filter(k -> k.getIdKorisnik() != korisnik.getIdKorisnik())
				.collect(Collectors.toList());
		return korisnici;
	}
	
	@RequestMapping(value = "/getNotifications", method = RequestMethod.GET, produces = "application/json")
	public List<Poruka> getNotifications(Principal principal) {
		Korisnik korisnik = korisnikService.getByUsername(principal.getName());
		List<Predmet> predmeti = predmetService.getZavrseneAukcijeByKorisnik(korisnik);
		for(Predmet p : predmeti) {
			p.setStatus((byte) 1);
			Licitacija pobednickaLicitacija = licitacijaService.getPobednickaLicitacija(p);
			porukaService.addNotification(korisnik, p, pobednickaLicitacija);
		}
		return porukaService.getNotifications(korisnik);
	}
	
	@RequestMapping(value = "/getPobednikaAukcije", method = RequestMethod.GET, produces = "application/json")
	public Korisnik getPobednikaAukcije(@RequestParam("idPredmeta") Integer idPredmet) {
		Predmet predmet = predmetService.getById(idPredmet);
		Licitacija pobednickaLicitacija =  licitacijaService.getPobednickaLicitacija(predmet);
		return pobednickaLicitacija == null ? null : pobednickaLicitacija.getKorisnik();
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
		Korisnik korisnik = korisnikService.getByUsername(currentUser.getName());
		predmet.setKorisnik(korisnik);
		return predmetService.addPredmet(predmet);
	}

	@RequestMapping(value = "/saveSlika", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Slika saveSlika(@RequestParam("idPredmet") Integer idPredmet,
			@RequestParam("imageFile") MultipartFile imageFile) {
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
		// Vraca null ako predmet ne postoji, pripada ulogovanom korisniku ili je aukcija za taj predmet zavrsena
		Predmet predmet = predmetService.getById(idPredmet);
		Korisnik korisnik = korisnikService.getByUsername(currentUser.getName());
		Date danas = new Date();
		if (predmet == null || korisnik.getIdKorisnik() == predmet.getKorisnik().getIdKorisnik() || danas.after(predmet.getKrajAukcije())) {
			return null;
		}
		Licitacija licitacija = new Licitacija();
		LicitacijaPK pk = new LicitacijaPK();
		pk.setKorisnik_idKorisnik(korisnik.getIdKorisnik());
		pk.setPredmet_idPredmet(predmet.getIdPredmet());
		licitacija.setDatumLicitacije(danas);
		licitacija.setId(pk);
		licitacija.setPonuda(ponuda);
		licitacija.setKorisnik(korisnik);
		licitacija.setPredmet(predmet);
		return licitacijaService.addLicitacija(licitacija);
	}

	@RequestMapping(value = "/saveOcena", method = RequestMethod.POST, produces = "application/json")
	public Ocena saveKomnetarIOcenu(@RequestParam("ocena") Integer ocena, @RequestParam("komentar") String komentar,
			@RequestParam("idKorisnik") Integer idKorisnik, Principal p) {
		Korisnik korisnik = korisnikService.getById(idKorisnik);
		Korisnik currentUser = korisnikService.getByUsername(p.getName());
		if (korisnik == null || idKorisnik == currentUser.getIdKorisnik()) {
			return null;
		}
		Ocena o = new Ocena();
		o.setKomentar(komentar);
		o.setOcena(ocena);
		o.setKorisnik(korisnik);
		return ocenaService.addOcena(o);

	}

	@RequestMapping(value="savePoruka", method = RequestMethod.POST, produces = "application/json")
	public Poruka savePoruka(@RequestParam("idKorisnik") Integer idKorisnik2, @RequestParam("text") String text, Principal principal) {
		Poruka poruka = new Poruka();
		poruka.setDatum(new Date());
		poruka.setTekstPoruke(text);
		Korisnik korisnik1 = korisnikService.getByUsername(principal.getName());
		Korisnik korisnik2 = korisnikService.getById(idKorisnik2);
		poruka.setKorisnik1(korisnik1);
		poruka.setKorisnik2(korisnik2);
		return porukaService.addPoruka(poruka);
	}
	
	@RequestMapping(value = "prosekOcena", method = RequestMethod.GET, produces = "application/json")
	public double prosekOcena(Principal currUser) {
		Korisnik korisnik= korisnikService.getByUsername(currUser.getName());
		return ocenaService.prosek(korisnik);
		
	}
}
