package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	@Column(name="`e-mail`")
	private String e_mail;

	private String imeKorisnika;

	private String password;

	private String prezimeKorisnika;

	private String username;

	//bi-directional many-to-one association to Uloga
	@ManyToOne
	private Uloga uloga;

	//bi-directional many-to-one association to Licitacija
	@OneToMany(mappedBy="korisnik")
	@Transient
	private List<Licitacija> licitacijas;

	//bi-directional many-to-one association to Ocena
	@OneToMany(mappedBy="korisnik")
	@Transient
	private List<Ocena> ocenas;

	//bi-directional many-to-one association to Poruka
	@OneToMany(mappedBy="korisnik1")
	@Transient
	private List<Poruka> porukas1;

	//bi-directional many-to-one association to Poruka
	@OneToMany(mappedBy="korisnik2")
	@Transient
	private List<Poruka> porukas2;

	//bi-directional many-to-one association to Predmet
	@OneToMany(mappedBy="korisnik")
	@Transient
	private List<Predmet> predmets;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getE_mail() {
		return this.e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getImeKorisnika() {
		return this.imeKorisnika;
	}

	public void setImeKorisnika(String imeKorisnika) {
		this.imeKorisnika = imeKorisnika;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezimeKorisnika() {
		return this.prezimeKorisnika;
	}

	public void setPrezimeKorisnika(String prezimeKorisnika) {
		this.prezimeKorisnika = prezimeKorisnika;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Uloga getUloga() {
		return this.uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public List<Licitacija> getLicitacijas() {
		return this.licitacijas;
	}

	public void setLicitacijas(List<Licitacija> licitacijas) {
		this.licitacijas = licitacijas;
	}

	public Licitacija addLicitacija(Licitacija licitacija) {
		getLicitacijas().add(licitacija);
		licitacija.setKorisnik(this);

		return licitacija;
	}

	public Licitacija removeLicitacija(Licitacija licitacija) {
		getLicitacijas().remove(licitacija);
		licitacija.setKorisnik(null);

		return licitacija;
	}

	public List<Ocena> getOcenas() {
		return this.ocenas;
	}

	public void setOcenas(List<Ocena> ocenas) {
		this.ocenas = ocenas;
	}

	public Ocena addOcena(Ocena ocena) {
		getOcenas().add(ocena);
		ocena.setKorisnik(this);

		return ocena;
	}

	public Ocena removeOcena(Ocena ocena) {
		getOcenas().remove(ocena);
		ocena.setKorisnik(null);

		return ocena;
	}

	public List<Poruka> getPorukas1() {
		return this.porukas1;
	}

	public void setPorukas1(List<Poruka> porukas1) {
		this.porukas1 = porukas1;
	}

	public Poruka addPorukas1(Poruka porukas1) {
		getPorukas1().add(porukas1);
		porukas1.setKorisnik1(this);

		return porukas1;
	}

	public Poruka removePorukas1(Poruka porukas1) {
		getPorukas1().remove(porukas1);
		porukas1.setKorisnik1(null);

		return porukas1;
	}

	public List<Poruka> getPorukas2() {
		return this.porukas2;
	}

	public void setPorukas2(List<Poruka> porukas2) {
		this.porukas2 = porukas2;
	}

	public Poruka addPorukas2(Poruka porukas2) {
		getPorukas2().add(porukas2);
		porukas2.setKorisnik2(this);

		return porukas2;
	}

	public Poruka removePorukas2(Poruka porukas2) {
		getPorukas2().remove(porukas2);
		porukas2.setKorisnik2(null);

		return porukas2;
	}

	public List<Predmet> getPredmets() {
		return this.predmets;
	}

	public void setPredmets(List<Predmet> predmets) {
		this.predmets = predmets;
	}

	public Predmet addPredmet(Predmet predmet) {
		getPredmets().add(predmet);
		predmet.setKorisnik(this);

		return predmet;
	}

	public Predmet removePredmet(Predmet predmet) {
		getPredmets().remove(predmet);
		predmet.setKorisnik(null);

		return predmet;
	}

	@Override
	public String toString() {
		return "imeKorisnika=" + imeKorisnika + ", prezimeKorisnika=" + prezimeKorisnika;
	}
	

}