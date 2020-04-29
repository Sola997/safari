package model;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the licitacija database table.
 * 
 */
@Entity
@NamedQuery(name = "Licitacija.findAll", query = "SELECT l FROM Licitacija l")
public class Licitacija implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LicitacijaPK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datumLicitacije;

	private byte pobedio;

	private int ponuda;

	// bi-directional many-to-one association to Korisnik
	@ManyToOne
	@MapsId("korisnik_idKorisnik")
	@JoinColumn(name = "korisnik_idKorisnik")
	private Korisnik korisnik;

	// bi-directional many-to-one association to Predmet
	@ManyToOne
	@MapsId("predmet_idPredmet")
	@JoinColumn(name = "predmet_idPredmet")
	private Predmet predmet;

	public Licitacija() {
	}

	public LicitacijaPK getId() {
		return this.id;
	}

	public void setId(LicitacijaPK id) {
		this.id = id;
	}

	public Date getDatumLicitacije() {
		return this.datumLicitacije;
	}

	public void setDatumLicitacije(Date date) {
		this.datumLicitacije = date;
	}

	public byte getPobedio() {
		return this.pobedio;
	}

	public void setPobedio(byte pobedio) {
		this.pobedio = pobedio;
	}

	public int getPonuda() {
		return this.ponuda;
	}

	public void setPonuda(int ponuda) {
		this.ponuda = ponuda;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Predmet getPredmet() {
		return this.predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

}